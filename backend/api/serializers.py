from django.contrib.auth.models import User
from rest_framework import serializers
from .models import MenuNote

class MenuNoteSerializer(serializers.ModelSerializer):
    class Meta:
        model = MenuNote
        fields = ('mn_queue', 'mn_table', 'mn_note')