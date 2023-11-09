import serial

# Reemplaza 'COM3' con el nombre del puerto de tu Arduino
port = 'COM5'

try:
    arduino = serial.Serial(port, 9600)
    print(f'Arduino en el puerto {port} está conectado.')
    arduino.close()
except serial.SerialException:
    print(f'Arduino en el puerto {port} no está conectado.')
