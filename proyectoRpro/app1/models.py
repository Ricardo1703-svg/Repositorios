from django.db import models

class Area(models.Model):
    nombre_area = models.CharField(max_length=100)
    descripcion = models.CharField(max_length=100)

class Cliente(models.Model):
    nombre = models.CharField(max_length=100)
    apellido = models.CharField(max_length=100)
    edad = models.IntegerField()
    dui = models.CharField(max_length=35)

class Empleado(models.Model):
    nombre = models.CharField(max_length=100)
    apellido = models.CharField(max_length=100)
    edad = models.IntegerField()
    area_id = models.ForeignKey(Area, on_delete=models.CASCADE)

class Venta(models.Model):
    fecha_venta = models.DateField()
    monto = models.FloatField()
    cliente_id = models.ForeignKey(Cliente, on_delete=models.CASCADE)
    empleado_id = models.ForeignKey(Empleado, on_delete=models.CASCADE)