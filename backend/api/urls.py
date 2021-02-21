from django.urls import path
from .views import (
    MenuNoteRequest,
    MenuNotePost,
    MenuNoteUpdate,
    MenuNoteDelete,
)

urlpatterns = [
    path('menunote/', MenuNoteRequest, name='MenuNoteRequest'),
    path('add/menunote/', MenuNotePost, name='MenuNotePost'),
    path('update/menunote/<int:pk>', MenuNoteUpdate, name='MenuNoteUpdate'),
    path('delete/menunote/<int:pk>', MenuNoteDelete, name='MenuNoteDelete'),
]