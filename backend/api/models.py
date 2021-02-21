from django.db import models
import random

# Create your models here.

class MenuNote(models.Model):
    mn_queue = models.AutoField(primary_key=True)
    mn_table = models.CharField(max_length=16)
    mn_note = models.CharField(max_length=5000)

    def json_get(self):
        return "{\"mn_queue\": "+ str(self.mn_queue) +", \"mn_table\": \"" + str(self.mn_table) +"\", \"mn_note\": \"" + str(self.mn_note) +"\"} "

    def __unicode__(self):
        return str(self.mn_queue)+"_" +str(self.mn_table)