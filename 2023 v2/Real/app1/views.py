from django.shortcuts import render
from .models import Proveedor, Fruta, Cliente
# Create your views here.

def lis_prov(request):
    proveedores = Proveedor.objects.all()
    return render(request, 'proveedores.html',{'proveedores': proveedores})

def lis_prod(request):
    productos = Fruta.objects.all()
    return render(request, 'productos.html',{'productos': productos})

def lis_cli(request):
    clientesdeR = Cliente.objects.all()
    return render(request, 'clientes.html',{'clientes': clientesdeR})