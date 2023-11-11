import tkinter as tk
import serial
import time

# Configura el puerto serie según tu Arduino (debes cambiar el puerto COM y la velocidad de baudios según tu configuración)
arduino_port = 'COM3'  # Cambia esto al puerto serial correcto
baud_rate = 9600

try:
    ser = serial.Serial(arduino_port, baud_rate)
except Exception as e:
    print(f"No se pudo conectar al puerto serial: {e}")
    exit()

root = tk.Tk()
root.title("Lectura de Temperatura y Humedad")

# Variables globales para almacenar los valores de temperatura y humedad
temperatura_actual = None
humedad_actual = None

temperature_label = tk.Label(root, text="Temperatura: N/A", font=("Arial", 16))
temperature_label.pack()

humidity_label = tk.Label(root, text="Humedad: N/A", font=("Arial", 16))
humidity_label.pack()


def update_values():
    global temperatura_actual, humedad_actual
    try:
        # Leer la línea de datos desde el Arduino y decodificarla
        data = ser.readline().decode('utf-8').strip()
        print(f"Datos recibidos: {data}")  # Agrega esta línea para imprimir los datos recibidos

        # Buscar la temperatura y la humedad en los datos recibidos
        temperature = None
        humidity = None

        # Dividir los datos en partes
        parts = data.split(',')
        for part in parts:
            if "Temperatura" in part:
                temperature = part.strip()
            elif "Humedad" in part:
                humidity = part.strip()

        if temperature is not None:
            temperatura_actual = temperature
            temperature_label.config(text="Temperatura: " + temperatura_actual)
        if humidity is not None:
            humedad_actual = humidity
            humidity_label.config(text="Humedad: " + humedad_actual)
    except Exception as e:
        print(f"Error al leer datos del puerto serial: {e}")

# Botón para detener/arrancar la lectura
reading = True
def toggle_reading():
    global reading
    reading = not reading
    if reading:
        read_button.config(text="Detener Lectura")
        update_values()
    else:
        read_button.config(text="Iniciar Lectura")

read_button = tk.Button(root, text="Detener Lectura", command=toggle_reading, font=("Arial", 14))
read_button.pack()

# Botón para guardar los datos en un archivo
def save_data():
    with open("data_log.txt", "a") as file:
        file.write(f"{time.strftime('%Y-%m-%d %H:%M:%S')}: {temperatura_actual}, {humedad_actual}\n")

save_button = tk.Button(root, text="Guardar Datos", command=save_data, font=("Arial", 14))
save_button.pack()

root.mainloop()

ser.close()
