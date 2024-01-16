import tkinter as tk
from tkinter import *
from tkinter import messagebox
from pymongo import MongoClient

# Conexion
client = MongoClient("mongodb://root12023:root2023@localhost:27017/?authMechanism=DEFAULT&authSource=admin")
db = client["Bodega"]
collection = db["Registros"]

# Funcion para insertar
def insertar_documento():
    nombre = nombre_entry.get()
    apellido = apellido_entry.get()
    edad = int(edad_entry.get())
    direccion = direccion_entry.get()
    celular = celular_entry.get()
    documento = {"nombre": nombre, "edad": edad, "apellido": apellido, "direccion": direccion, "celular": celular}
    result = collection.insert_one(documento)
    messagebox.showinfo("Genial", f"Documento insertado con id: {result.inserted_id}!")

# Funcion para buscar
def buscar_documentos():
    resultados.delete(0, tk.END)
    for doc in collection.find():
        resultados.insert(tk.END, f"Nombre: {doc['nombre']},Apellido: {doc['apellido']},Edad: {doc['edad']},Dirección: {doc['direccion']},Celular: {doc['celular']}")

# Funcion para Actualizar
def actualizar_documento():
    seleccionado = resultados.curselection()
    if seleccionado:
        indice = seleccionado[0]
        documento = collection.find_one({"nombre": resultados.get(indice).split(',')[0].split(': ')[1].strip()})
        if documento:
            nuevo_nombre = nombre_entry.get()
            nuevo_apellido = apellido_entry.get()
            nueva_edad = int(edad_entry.get())
            nueva_direccion = direccion_entry.get()
            nuevo_celular = celular_entry.get()
            # Actualiza los campos en la base de datos
            collection.update_one({"_id": documento["_id"]}, {
                "$set": {
                    "nombre": nuevo_nombre,
                    "apellido": nuevo_apellido,
                    "edad": nueva_edad,
                    "direccion": nueva_direccion,
                    "celular": nuevo_celular
                }
            })
            messagebox.showinfo("Actualizado", f"Documento actualizado con _id: {documento['_id']}")
        else:
            messagebox.showerror("Error", "No se encontró el documento a actualizar")
    else:
        messagebox.showwarning("Advertencia", "Selecciona un documento para actualizar")


# Funcion para Eliminar
def eliminar_documento():
    seleccionado = resultados.curselection()
    if seleccionado:
        indice = seleccionado[0]
        documento = collection.find_one({"nombre": resultados.get(indice).split(',')[0].split(': ')[1].strip()})
        if documento:
            collection.delete_one({"_id": documento["_id"]})
            messagebox.showinfo("Eliminado", f"Documento eliminado con _id: {documento['_id']}")
        else:
            messagebox.showerror("Error", "No se encontró el documento a eliminar")
    else:
        messagebox.showwarning("Advertencia", "Selecciona un documento para eliminar")

# Funcion para Limpiar campos
def limpiar_campos():
    nombre_entry.delete(0, END)
    apellido_entry.delete(0, END)
    edad_entry.delete(0, END)
    direccion_entry.delete(0, END)
    celular_entry.delete(0, END)

# Crear Ventana
ventana = tk.Tk()
ventana.title("CRUD de Registros en MongoDB")
ventana.geometry("800x600")

# Elementos de la GUI
#---------------------Nombre---------------------------------------
nombre_label = tk.Label(ventana, text="Nombre:",font=("Algerian"))
nombre_entry = tk.Entry(ventana)

#---------------------Apellido-------------------------------------
apellido_label = tk.Label(ventana, text="Apellido:",font=("Algerian"))
apellido_entry = tk.Entry(ventana)

#---------------------Edad-----------------------------------------
edad_label = tk.Label(ventana, text="Edad:",font=("Algerian"))
edad_entry = tk.Entry(ventana)

#---------------------Direccion------------------------------------
direccion_label = tk.Label(ventana, text="Dirección:",font=("Algerian"))
direccion_entry = tk.Entry(ventana)
#---------------------Celular---------------------------------------
celular_label = tk.Label(ventana, text="Celular:",font=("Algerian"))
celular_entry = tk.Entry(ventana)

#-----------------------------------------------------------Botones-------------------------------------------------------------
insertar_button = tk.Button(ventana, text="Guardar", command=insertar_documento, bg="#8bff82",font = ("Arial Black", 11))
buscar_button = tk.Button(ventana, text="Consultar", command=buscar_documentos,bg="#07FBEF",font = ("Arial Black", 11))
eliminar_button = tk.Button(ventana, text="Eliminar", command=eliminar_documento,bg="#F82727",font = ("Arial Black", 11))
actualizar_button = tk.Button(ventana, text="Actualizar", command=actualizar_documento,bg="#ADADA2",font = ("Arial Black", 11))
limpiar_button = tk.Button(ventana, text="Nuevo", command=limpiar_campos,bg="#E2E211",font = ("Arial Black", 11))

#-----------------------------------------Ventana---------------------------------------
resultados = tk.Listbox(ventana, width=70, height=10,font = ("Arial Black",10),fg="black")
#---------------------------------------------------------------------------------------

#COMENTARIO
#POR SI SE TE OLVIDA MAQUINA Y ES LA ALTURA Y X ES LO ANCHO DE LA HOJA

# Elementos de Ventana
#-----------------CAMPOS-----------------
nombre_label.place(x=10, y=10)
nombre_entry.place(x=100, y=10)
apellido_label.place(x=300, y=10)
apellido_entry.place(x=400, y=10)
edad_label.place(x=10, y=40)
edad_entry.place(x=100, y=40)
direccion_label.place(x=300, y=40)
direccion_entry.place(x=400, y=40)
celular_label.place(x=10, y=70)
celular_entry.place(x=100, y=70)
#-----------------BOTONES-----------------
insertar_button.place(x=10, y=100)
buscar_button.place(x=100, y=100)
eliminar_button.place(x=210, y=100)
actualizar_button.place(x=300, y=100)
limpiar_button.place(x=410, y=100)
#-----------------CUADRO-----------------
resultados.place(x=10, y=150)
resultados.configure(bg="#F85959")
# Iniciar
ventana.mainloop()