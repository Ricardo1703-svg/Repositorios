from django.shortcuts import render
from .models import Area,Cliente,Empleado,Venta

def lista_area(request):
    Areas = Area.objects.all()
    return render(request, 'area.html',{'Areas': Areas})

def lista_cliente(request):
    Clientes = Cliente.objects.all()
    return render(request, 'cliente.html',{'Clientes': Clientes})

def lista_empleado(request):
    Empleados = Empleado.objects.all()
    return render(request, 'empleado.html',{'Empleados': Empleados})

def lista_venta(request):
    Ventas = Venta.objects.all()
    return render(request, 'venta.html',{'Ventas': Ventas})