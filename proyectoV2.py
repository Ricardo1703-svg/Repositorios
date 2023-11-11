import serial
import tkinter

# Crea un objeto de la clase Serial
arduino = serial.Serial("COM5", 9600)

# Crea una ventana de Tkinter
ventana = tkinter.Tk()

# Crea un texto de Tkinter para mostrar la temperatura
temperatura_texto = tkinter.Label(ventana, text="Temperatura:")
temperatura_texto.grid(row=0, column=0)

# Crea un texto de Tkinter para mostrar la humedad
humedad_texto = tkinter.Label(ventana, text="Humedad:")
humedad_texto.grid(row=1, column=0)

# Crea un texto de Tkinter para mostrar los valores de temperatura y humedad
valor_temperatura = tkinter.Label(ventana, text="")
valor_temperatura.grid(row=0, column=1)

valor_humedad = tkinter.Label(ventana, text="")
valor_humedad.grid(row=1, column=1)

# Crea una función para actualizar los valores de temperatura y humedad
def actualizar_valores():
    # Lee los datos del Arduino
    datos = arduino.readline().decode("utf-8")

    # Divide los datos en dos partes
    temperatura, humedad = datos.split(",")

    # Actualiza los valores de temperatura y humedad en los textos de Tkinter
    valor_temperatura.config(text=temperatura)
    valor_humedad.config(text=humedad)

    # Actualiza la ventana
    ventana.update()

# Crea un botón de Tkinter para actualizar los valores de temperatura y humedad
boton_actualizar = tkinter.Button(ventana, text="Actualizar", command=actualizar_valores)
boton_actualizar.grid(row=2, column=0)

# Inicia el bucle principal de Tkinter
ventana.mainloop()
