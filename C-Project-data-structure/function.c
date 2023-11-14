#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "project.h"


void clear_memory(BusStop *headstop, BusLines *headlines){
   BusLines *l;
   BusStop *s;

   for(l = headlines;l != NULL; l = l->next){
      free_line(l);
   }
   for(s = headstop; s!= NULL; s = s->next){
      free_stop(s);
   }
   return;
}

void NoMemory(BusStop *headstop, BusLines *headline, char *cmd){
   free(cmd);
   clear_memory(headstop, headline);
   printf("No memory.");
   exit(1);
}




int compare_line(BusLines *head, char *name){/*-Compare name with all the lines, if exist return 1-*/ 
   BusLines *t;

   for(t = head; t != NULL; t = t->next){
      if(strcmp(t->name, name) == 0){
         return 1;
      }
   }
   return 0;
}

int compare_stop(BusStop *head, char *name){
   BusStop *t;

   for(t = head; t != NULL; t = t->next){
      if(strcmp(t->name, name) == 0){
         return 1;
      }
   }
   return 0;
}

int verify_inverso(){ /*-verify if exits a word after the busline and if it does verify if its the word inverso or at least 3 characters*/
    char inverso[7] = "inverso";
    char word[MAX_SIZE - 4];
    int i, m ,l = 0;
    char j;
    j = getchar();
    if(j == ' '){
        if(scanf("%s", word)){}
        m = strlen(word);
        if(m >= 3){
            for(i = 0; i < m;i++){
                if(word[i] != inverso[i]){
                    printf("incorrect sort option.\n");
                    break;
                }
                else{
                    l = 1;
                }
            }    
        }
        else{
            printf("incorrect sort option.\n");
        }
    }
    else{
        l = 0;
    }
    return l;
}

void list_busline_stops(BusLines *head, char *name){
   BusLines *t;
   Names *p;

   for(t = head; t != NULL; t = t->next){
      if(strcmp(t->name, name) == 0){
         if(t->stops_number == 2){
            printf("%s, %s\n", t->origin_stop, t->destiny_stop);
         }
         else{
            p = t->stops;
            while(p->next != NULL){
               printf("%s, ", p->name);
               p = p->next;
            }
            printf("%s\n", p->name);
         }
      }
   } 
}

void list_busline_stops_inverted(BusLines *head, char *name){
   BusLines *t;
   Names *p;

   for(t = head; t != NULL; t = t->next){
      if(strcmp(t->name, name) == 0){
         if(t->stops_number == 2){
            printf("%s, %s", t->destiny_stop, t->origin_stop);
         }
         else{
            p = t->stop_inv;
            while(p->next != NULL){
               printf("%s, ", p->name);
               p = p->next;
            }
            printf("%s\n", p->name);
         }
      }
   } 
}
void list_busline(BusLines *head){ 
   BusLines *t;
   for(t = head; t != NULL; t = t->next){
      if(strlen(t->name) < 6){
         if(t->stops_number > 0){
            printf("%s %s %s %d %.2f %.2f\n", t->name,t->origin_stop, t->destiny_stop,
            t->stops_number,t->cost, t->duration);
         }
         else{
            printf("%s %d %.2f %.2f\n", t->name, t->stops_number,t->cost, t->duration);
         }
      }
   }
}
char remove_quotes(char name[]){/*if the string has white characters removes the quotes*/
    char w;
    char next;
    int l = 0;
    if((w = getchar()) == '\"'){
        while((w = getchar()) != '\"'){
            name[l] = w;
            l++;
        }
    name[l] = '\0';
    next = getchar();
    }
    else{
        name[0] = w;
        l = 1;
        while((w = getchar())!= 32 && w != '\n'){
            name[l] = w;
            l++;
        }
    name[l] = '\0';
    next = w;
    }
   return next;
}
void list_busstops(BusStop *head){ 
   BusStop *t;
   for(t = head; t != NULL; t = t->next){
        printf("%s: %16.12f %16.12f %d\n", t->name,t->latitude, t->longitude,
        t->number_buslines);
    }
}
int manage_p(int already_ext, BusStop *head, char *name_s){
   BusStop *t;
   for(t = head; t != NULL; t = t->next){
      if((strcmp(t->name, name_s) == 0 ) && (already_ext == 1)){
         printf("%16.12f %16.12f\n", t->latitude, t->longitude);
         return 0;
      }
      else if(strcmp(t->name, name_s) == 0 && already_ext == 0){ /*if you try to create a bus stop that already exist*/
         printf("%s: stop already exists.\n", name_s);
         return 0;
      }
   }
   if(already_ext == 1){ /*if doesn't exit and you tried to get is coordinates*/
      printf("%s: no such stop.\n", name_s);
      return 0;
   }
   else if(already_ext == 0){
      return 1;
      
   }
   return 0;
}
Names* create_new_name(BusStop *headstop ,BusLines *headline,char *cmd ){
   Names *new_name = (Names*)malloc(sizeof(Names));
   if(!new_name){
      NoMemory(headstop ,headline,cmd);
   }
   new_name->next = NULL;
   return new_name;
}
Link* create_new_link(double cost, double duration,BusStop *headstop ,BusLines *headline,char *cmd ){
   Link *new_link = (Link*)malloc(sizeof(Link));
   if(!new_link){
      NoMemory(headstop ,headline,cmd);
   }
   new_link->cost = cost;
   new_link->duration = duration;
   new_link->next = NULL;
   return new_link;
}

int verify_link(BusStop *headstop ,BusLines *headline,char *cmd ,char *s_name_d, char *s_name_o, char *l_name, double cost, double duration){
   BusLines *t; 
   BusStop *p;
   Names *k;
   Names *new_name;
   Names *new_name_d;
   Names *new_name_d_i;
   Names *new_name_o_i;  
   Names *new_name_o;
   Names *s_new_name_o;
   Names *s_new_name_d;
   Link *new_link;

   int n = 0;/*if the for pass througt the both line breaks the for*/
   int new_stop = 0; /*use to verify if the stop is new*/

   for(t = headline; t != NULL; t = t->next){
      if(strcmp(t->name, l_name) == 0){
         if(t->stops_number > 0){
            if(strcmp(s_name_d, t->origin_stop) != 0 && strcmp(s_name_o,t->destiny_stop) != 0 ){
               printf("link cannot be associated with bus line.\n");
               return 1; 
            }
            else if(strcmp(s_name_o, t->destiny_stop) == 0){         
               t->stops_number++;
               t->cost = t->cost + cost;
               t->duration = t->duration + duration;
               new_link = create_new_link(cost, duration,headstop ,headline ,cmd);
               for(p = headstop; p != NULL; p = p->next){
                  if(strcmp(p->name, s_name_d) == 0){
                     t->destiny_stop = p->name;
                     new_name_d = create_new_name(headstop ,headline,cmd);
                     new_name_o_i = create_new_name(headstop ,headline,cmd);
                     new_name_d->name = p->name; 
                     new_name_o_i->name = p->name;
                     k = t->stops;
                     n++;
                     while (k->next != NULL) { /*create a stop in normal order*/
                        k = k->next;
                     }
                     k->next = new_name_d;
                     new_name_o_i->next = t->stop_inv;
                     t->stop_inv = new_name_o_i;
                     for(k = p->lines; k != NULL; k = k->next){
                        if(strcmp(k->name, l_name) == 0){
                           new_stop = 1;
                           break;
                        }
                     }
                     if(new_stop == 0){
                        new_name = create_new_name(headstop ,headline,cmd);
                        new_name->name = t->name;
                        new_name->next = p->lines;
                        p->lines = new_name;
                        p->number_buslines++;
                     }
                  }
                  else if(strcmp(p->name, s_name_o) == 0){
                     n++;
                  }
                  else if (n == 2){
                     break;
                  }
               }
               while (t->links->next != NULL) {
                  t->links = t->links->next;
               }
               t->links->next = new_link;
            }
            else if(strcmp(s_name_d, t->origin_stop) == 0){
               t->stops_number++;
               t->cost = t->cost + cost;
               t->duration = t->duration + duration;
               new_link = create_new_link(cost, duration, headstop ,headline,cmd);
               for(p = headstop; p != NULL; p = p->next){
                  if(strcmp(p->name, s_name_o) == 0){
                     t->origin_stop = p->name;
                     new_name_o = create_new_name(headstop ,headline,cmd);
                     new_name_d_i = create_new_name(headstop ,headline,cmd);
                     new_name_o->name = p->name;   /*create a stop in normal order*/
                     new_name_o->next = t->stops;
                     t->stops = new_name_o;
                     new_name_d_i->name = p->name;   /*create a stop in inverse order*/
                     k = t->stop_inv;
                     while (k->next != NULL) {
                        k = k->next;
                     }
                     k->next = new_name_d_i;
                     for(k = p->lines; k != NULL; k = k->next){
                        if(strcmp(k->name, l_name) == 0){
                           new_stop = 1;
                           break;
                        }
                     }
                     if(new_stop == 0){
                        new_name = create_new_name(headstop ,headline,cmd);
                        new_name->name = t->name;
                        new_name->next = p->lines;
                        p->lines = new_name;
                        p->number_buslines++;
                     }
                  }
                  else if(strcmp(p->name, s_name_d) == 0){
                     n++;
                  }
                  else if (n == 2){
                     break;
                  }
               }
               while (t->links->next != NULL) {
                  t->links = t->links->next;
               }
               t->links->next = new_link;
               return 0;
            }
         }   
         else{
            if(t->stops_number == 0){ 
               t->stops_number = 2;
               t->cost = t->cost + cost;
               t->duration = t->duration + duration;
               new_link = create_new_link(cost, duration,headstop ,headline,cmd);
               t->links = new_link;
               new_name_d = create_new_name(headstop ,headline,cmd);
               new_name_o = create_new_name(headstop ,headline,cmd);
               new_name_d_i = create_new_name(headstop ,headline,cmd);
               new_name_o_i = create_new_name(headstop ,headline,cmd);
               for(p = headstop; p != NULL; p = p->next){
                  if(strcmp(s_name_d, s_name_o) == 0){
                     p->number_buslines = -1;
                  }
                  if(strcmp(p->name, s_name_d) == 0){
                     t->destiny_stop = p->name;
                     s_new_name_d = create_new_name(headstop ,headline,cmd);
                     s_new_name_d->name = t->name;
                     if(p->number_buslines == 0){
                        p->lines = s_new_name_d;
                     }
                     else if(p->number_buslines > 0){
                        s_new_name_d->next = p->lines;
                        p->lines = s_new_name_d;
                     }
                     new_name_d->name = p->name;
                     new_name_o_i->name = p->name;
                     p->number_buslines++;
                  }
                  if(strcmp(p->name, s_name_o) == 0){
                     t->origin_stop = p->name;
                     s_new_name_o = create_new_name(headstop ,headline,cmd);
                     s_new_name_o->name = t->name;
                     if(p->number_buslines == 0){
                        p->lines = s_new_name_o; 
                     }
                     else if(p->number_buslines > 0){
                        s_new_name_o->next = p->lines;
                        p->lines = s_new_name_o;
                     }
                     new_name_o->name = p->name;
                     new_name_d_i->name = p->name;
                     p->number_buslines++;
                  }
               }
               t->stops = new_name_o; /*create a stop in normal order*/
               new_name_o->next = new_name_d;
               t->stop_inv = new_name_o_i; /*create a stop in inverse order*/
               new_name_o_i->next = new_name_d_i;
               return 0;
            }
         }
      }
   }
   return 0;
}

Names* remove_line_from_stop(BusStop *headS,Names *head, char *l_name){
   Names *current = head;
   Names *prev = NULL;
   BusStop *s = headS;

   if(strcmp(current->name, l_name) == 0){
      head = current->next;
      s->number_buslines--;
      free(current);
      return head;
   }
   else{
      while (current != NULL && strcmp(current->name, l_name) != 0) {
         prev = current;
         current = current->next;
      }
      if (current == NULL) {
         return head;
      }
      prev->next = current->next;
      free(current);
      s->number_buslines--;
      return head;
      
   }
   free(current);
   return head;
}


void free_line(BusLines *line){ /*free lines*/
   BusLines *l = line;

   free(l->links);
   l->links = NULL;
   free(l->stops);
   l->stops = NULL;
   free(l->stop_inv);
   l->stop_inv = NULL;
   l->destiny_stop = NULL;
   l->origin_stop = NULL;
   free(l->name);
   l->cost = 0;
   l->duration = 0;
   l->stops_number = 0;
   return;
}

void remove_stop_from_line(BusLines *headL ,char *s_name){
   BusLines *l = headL;
   Names *current = headL->stops;
   Names *current_inv = headL->stop_inv;
   Names *prev_inv = NULL;
   Names *prev = NULL;
   Link *link = headL->links;

   if(l->stops_number <= 2){ /*if theres only two stops clears the line*/
      if(strcmp(l->destiny_stop, s_name) == 0 || strcmp(l->origin_stop, s_name) == 0){
         headL->stops = NULL;
         headL->stop_inv = NULL;
         l->links = NULL;
         l->cost -= link->cost;
         l->duration-= link->duration;
         l->origin_stop = NULL;
         l->destiny_stop = NULL;
         l->stops_number = 0;
         return;
      }
      else{
         return;
      }
   } 
   if(strcmp(current->name, s_name) == 0){ /*remove stop from list of stop and remove the links if stop is the origin*/
      headL->stops = current->next; /*remove the first stop*/
      while (current_inv->next!= NULL){ /*remove the last stop of the reversed list*/
         prev_inv = current_inv;
         current_inv = current_inv->next;
      }
      prev_inv->next = current_inv->next;
      l->links = link->next; /*Remove the first link*/
      l->cost -= link->cost;
      l->duration-= link->duration;
      l->origin_stop = current->next->name ; /*change the origin*/
      free(link);
      free(current);
      free(current_inv);
      l->stops_number--;
      return;
   }
   else{
      while (current != NULL && strcmp(current->name, s_name) != 0) {
         prev = current;
         current = current->next;
      }
      if (current == NULL) {
         return;/*return if the line doesn't have the stop*/
      }
      prev->next = current->next; /*removes the stop*/
      if(current->next == NULL){ /*the stop was the last stop aka the destiny of the line*/
         while(link->next->next != NULL){
            link = link->next;
         }
         l->cost -= link->next->cost;
         l->duration -= link->next->duration;
         l->destiny_stop = prev->name;
         free(link->next); /*remove the last link*/
      }
   }
   while (current_inv != NULL && strcmp(current_inv->name, s_name) != 0){ /*removes the stop from the inversed list*/
         prev_inv = current_inv;
         current_inv = current_inv->next;
      }
   if(strcmp(current_inv->name, s_name) == 0){
      headL->stop_inv = current_inv->next;
   }
   else{
      prev_inv->next = current->next;
   }
   l->stops_number--;
   free(current);
   free(current_inv);
   return;
}

void free_stop(BusStop *stop){
   stop->latitude = 0;
   stop->number_buslines = 0;
   stop->longitude = 0;
   free(stop->name);
   free(stop->lines);
   stop->lines = NULL;
}