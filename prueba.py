 import serial
import time

ser = serial.Serial('COM3', 9600)

while True:
    try:
        line = ser.readline().decode('utf-8').strip()
        if line:
            temp_str, hum_str = line.split(',')
            temperatura = float(temp_str.split(':')[1])
            humedad = float(hum_str.split(':')[1])
            print(f'Temperatura: {temperatura}°C, Humedad: {humedad}%')
        else:
            time.sleep(1)  # Espera 1 segundo antes de intentar leer nuevamente
    except serial.SerialException as e:
        print(f"Error en la comunicación serie: {e}")
        break
