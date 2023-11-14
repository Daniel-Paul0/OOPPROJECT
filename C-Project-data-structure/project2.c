#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "project.h"



/*-----------Command c funcion---------------*/

void BusLines_funcion(BusLines **headlines, BusStop **headstop, char *cmd){
   char *name = (char *)malloc(sizeof(char) * MAX_SIZE - 2);
   char *c = (char *)malloc(sizeof(char));
   if(!name){
      NoMemory(*headstop, *headlines, cmd);
   } 
   if(!c){
      free(name);
      NoMemory(*headstop, *headlines, cmd);
   }

   *c = getchar();
   if (*c == '\n'){
     list_busline(*headlines);
      free(c);
      free(name);
      return;
   }
   else if (*c == ' '){
      if(scanf("%s", name)){};
      if(compare_line(*headlines ,name) == 1){ /* bus_stops_print*/
         if(verify_inverso()== 0){
               list_busline_stops(*headlines, name); 
            }
            else{
               list_busline_stops_inverted(*headlines, name);
            }
            free(c);
            free(name);
            return;
      }
      else {
            BusLines *newNode = (BusLines*) malloc(sizeof(BusLines));
            if(!newNode){
               free(name);
               free(c);
               NoMemory(*headstop, *headlines, cmd);
            }
            newNode->name =malloc((sizeof(char))*(strlen(name)+1));
            if(!newNode->name){
               free(name);
               free(c);
               NoMemory(*headstop, *headlines, cmd);
            }
            newNode->next = NULL;
            newNode->stops_number = 0;
            newNode->cost = 0;
            newNode->duration = 0;
            strcpy(newNode->name, name);
            if (*headlines == NULL) {
                *headlines = newNode;
            } 
            else {
                BusLines *temp = *headlines;
                while (temp->next != NULL) {
                    temp = temp->next;
                }
                temp->next = newNode;
            }
         }
      }
   free(c);
   free(name);
   return;
}

/*------------Command p funcion---------------*/

void BusStop_funcion(BusLines **headlines, BusStop **headstop, char *cmd){   
   char *name_s = (char *)malloc(sizeof(char) * MAX_SIZE - 2);
   char *c = (char *)malloc(sizeof(char));
   int already_ext = 0;
   char nxt;
   if(!name_s){
      NoMemory(*headstop, *headlines, cmd);
   } 
   if(!c){
      free(name_s);
      NoMemory(*headstop, *headlines, cmd);
   } 
   
    
   *c = getchar();
   if (*c == '\n'){ /*prints all bus stops information*/
      list_busstops(*headstop);
     return;
   }
   else if (*c == ' '){
      nxt = remove_quotes(name_s);
   
      if(nxt == ' '){
         already_ext = 0;
      }
      else{
         already_ext = 1;
      }
   }
   if(manage_p(already_ext, *headstop, name_s) == 1){
      BusStop *newNode = (BusStop*) malloc(sizeof(BusStop));
      if(!newNode){
               free(name_s);
               free(c);
               NoMemory(*headstop, *headlines, cmd);
            }
      newNode->name =malloc((sizeof(char))*(strlen(name_s)+1));
      if(!newNode->name){
               free(name_s);
               free(c);
               NoMemory(*headstop, *headlines, cmd);
            }
      newNode->longitude = 0;
      newNode->latitude = 0;
      newNode->next = NULL;
      strcpy(newNode->name, name_s);
      if(scanf("%lf %lf",&newNode->latitude, &newNode->longitude)){};
      if(newNode->latitude > 90 || newNode->latitude < -90){
         printf("invalid location.\n");
         return;
      }
      if(newNode->longitude > 180 || newNode->longitude < -180){
         printf("invalid location.\n");
         return;
      }
      if (*headstop == NULL) {
         *headstop = newNode;
      } 
      else {
         BusStop *temp = *headstop;
         while (temp->next != NULL) {
            temp = temp->next;
         }
         temp->next = newNode;
      }
   }
   free(c);
   free(name_s);
   return;
} 

/*-------------Comand l funcion-------------*/
void Buslinks_funcion(BusLines **headline, BusStop **headstop, char *cmd){
   double cost, duration;
   char *l_name = (char *)malloc(sizeof(char) * MAX_SIZE - 4);
   char *s_name_o = (char *)malloc(sizeof(char) * MAX_SIZE - 6);
   char *s_name_d = (char *)malloc(sizeof(char) * MAX_SIZE - 8);
   char *c = (char *)malloc(sizeof(char));

   if(scanf("%s",l_name)){}
   if(compare_line(*headline, l_name) == 0){
      printf("%s: no such line.\n",l_name);
      while((*c = getchar())!= '\n'){ /*a getchar until \n for cleaning the line*/
      }
      free(l_name);
      free(s_name_o);/*free the memory that will not be used*/
      free(s_name_d);
      free(c);
      return;
   }
   getchar();
   remove_quotes(s_name_o);
   if(compare_stop(*headstop, s_name_o) == 0){
      printf("%s: no such stop.\n",s_name_o);
      while((*c = getchar())!= '\n'){ /*a getchar until \n for cleaning the line*/
      }
      free(l_name);
      free(s_name_o);/*free the memory that will not be used*/
      free(s_name_d);
      free(c);
      return;
   }
   remove_quotes(s_name_d);
   if(compare_stop(*headstop, s_name_d) == 0){
      printf("%s: no such stop.\n",s_name_d);
      while((*c = getchar())!= '\n'){ /*a getchar until \n for cleaning the line*/
      }
      free(l_name);
      free(s_name_o);/*free the memory that will not be used*/
      free(s_name_d);
      free(c);
      return;
   }
   if(scanf("%lf %lf", &cost, &duration)){}
   if((cost < 0) || (duration < 0)){
      printf("negative cost or duration.\n");
      free(l_name);
      free(s_name_o);/*free the memory that will not be used*/
      free(s_name_d);
      free(c);
      return;
   }
   if(verify_link(*headstop ,*headline,cmd,s_name_d, s_name_o,l_name,cost,duration) == 1){
      return;
   }
      free(c);
}
/*-------------Comand i funcion-------------*/

void BusIntersection_funcion(BusStop **heads, BusLines **headl, char *cmd){
   BusStop *t;
   Names *i;
   Names *p;
   Names *l;
   
   for(t = *heads; t != NULL; t = t->next){
      if(t->number_buslines > 1){
         printf("%s %d:",t->name ,t->number_buslines);
         for (i = t->lines; i != NULL; i = i->next){
            for(p = i->next; p!= NULL;p = p->next){
               if(strcmp(i->name, p->name) > 0){
                  l = create_new_name(*heads, *headl, cmd);
                  l->name = i->name;
                  i->name = p->name;
                  p->name = l->name;
                  free(l); 
               }
            }
         }
         for(i = t->lines; i->next != NULL; i = i ->next){
               printf(" %s", i->name);
         }
         printf(" %s\n", i->name);
      }     
   }
   return;
}
/*-------------Comand r funcion-------------*/

void Remove_Line(BusStop **headstop, BusLines **headline){
   BusLines *l = *headline;
   BusStop *s;
   BusLines *prev = NULL;
   char *l_name = (char *)malloc(sizeof(char) * MAX_SIZE - 4);

   if(scanf("%s",l_name)){}
   if(compare_line(*headline , l_name) == 0){
      printf("%s: no such line.\n", l_name);
      free(l_name);
      return;
   }
   for(s = *headstop; s != NULL; s = s->next){
      if(s->number_buslines > 0){
         s->lines = remove_line_from_stop(s,s->lines, l_name);
      }
   }
   if(strcmp(l->name, l_name) == 0){
         *headline = l->next;
         free_line(l);
         free(l);
         return;
      }
   else{
      while (l != NULL && strcmp(l->name, l_name) != 0) {
         prev = l;
         l = l->next;
      }
      prev->next = l->next;
      free_line(l);
   }
   free(l_name);
   return;
}
/*-------------Comand e funcion-------------*/

void Remove_Stop(BusStop **headstop, BusLines **headline){
   BusLines *l;
   BusStop *s = *headstop;
   BusStop *prev = NULL;
   char *s_name = (char *)malloc(sizeof(char) * MAX_SIZE - 2);

   if(scanf("%s",s_name)){}
   if(compare_stop(*headstop , s_name) == 0){
      printf("%s: no such stop.\n", s_name);
      free(s_name);
      return;
   }
   for(l = *headline; l != NULL; l = l->next){
      if(l->stops_number > 0){
         remove_stop_from_line(l, s_name);
      }
   }
   if(strcmp(s->name, s_name) == 0){
         *headstop = s->next;
         free_stop(s);
         free(s);
         return;
      }
   else{
      while (s != NULL && strcmp(s->name, s_name) != 0) {
         prev = s;
         s = s->next;
      }
      prev->next = s->next;
      free_stop(s);
      free(s);
   }
   free(s_name);
   return;
}

/*-------------Comand a funcion-------------*/
void clear_system(BusStop **headstop, BusLines **headlines){
   BusLines *l;
   BusStop *s;

   for(l = *headlines;l != NULL; l = l->next){
      free_line(l);
   }
   for(s = *headstop; s!= NULL; s = s->next){
      free_stop(s);
   }

   return;
}

void show_last_stop(BusLines **headl, BusStop **heads){
   char *s_name = (char *)malloc(sizeof(char) * MAX_SIZE - 2);
   BusLines *t;

   if(scanf("%s", s_name)){}
   if(compare_stop(*heads, s_name) == 0){
      printf("%s: no such stop.\n", s_name);
   }
   else{
      for(t = *headl; t->next; t = t->next){
         if(strcmp(t->destiny_stop, s_name) == 0){
            printf("%s ", t->name);
         }
      }
   }


   free(s_name);
}



int main(){ /*main program redirect the program to the function se*/
   char *c = (char *)malloc(sizeof(char));
   BusLines *headlines = NULL;
   BusStop *headstop = NULL;


   while((*c = getchar()) != 'q'){
      if(*c == 'c'){
         BusLines_funcion(&headlines, &headstop, c);
      }
      if(*c == 'p'){
         BusStop_funcion(&headlines, &headstop, c);
      }
      if(*c == 'l'){
         Buslinks_funcion(&headlines, &headstop, c);
      }
      if(*c == 'i'){
         BusIntersection_funcion(&headstop, &headlines, c);
      }
      if(*c == 'r'){
         Remove_Line(&headstop, &headlines);
      }
      if(*c == 'e'){
         Remove_Stop(&headstop, &headlines);
      }
      if(*c == 'a'){
         clear_system(&headstop, &headlines);
         free(c);
         main();
         return 0;
      }
      if(*c == 'f'){
         show_last_stop(&headlines, &headstop);
      }
   }
   clear_system(&headstop, &headlines);
   free(c);
   return 0;
}
