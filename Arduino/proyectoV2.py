import tkinter as tk
from time import strftime

def actualizar_hora():
    string_hora = strftime('%H:%M:%S %p')
    etiqueta_hora.config(text=string_hora)
    
    string_fecha = strftime('%d/%m/%Y')
    etiqueta_fecha.config(text=string_fecha)

    ventana.after(1000, actualizar_hora)

ventana = tk.Tk()
ventana.title("Reloj en tiempo real")

etiqueta_hora = tk.Label(ventana, font=('calibri', 40, 'bold'), background='black', foreground='white')
etiqueta_hora.pack(anchor='center')

etiqueta_fecha = tk.Label(ventana, font=('calibri', 20, 'bold'), background='black', foreground='white')
etiqueta_fecha.pack(anchor='center')

actualizar_hora()

ventana.mainloop()