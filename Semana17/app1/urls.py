from django.urls import path
from . import views

urlpatterns = [
    path('areas/', views.lista_area, name='lista_area'),
    path('clientes/', views.lista_cliente, name='lista_cliente'),
    path('empleados/', views.lista_empleado, name='lista_empleado'),
    path('ventas/', views.lista_venta, name='lista_venta'),
    
]