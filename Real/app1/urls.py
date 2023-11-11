from django.urls import path
from . import views

urlpatterns = [
    path('proveedores/', views.lis_prod, name='lis_prov'),
    path('productos/', views.lis_prod, name='lis_prod'),
    path('clientes/', views.lis_cli, name='lis_cli'),
    
]