import serial
import time

# Reemplaza 'COM3' con el nombre del puerto de tu Arduino
serialArduino = serial.Serial("COM3", 9600)
time.sleep(1)

while True:
    cad = serialArduino.readline().decode('ascii')
    print(cad)
    print("------------------------")