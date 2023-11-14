#ifndef PROJECT2_H
#define PROJECT2_H

#define MAX_SIZE 65536


typedef struct names{
    char *name;
    struct names *next;
}Names;

typedef struct busstop {
    char *name;
    double longitude;
    double latitude;
    int number_buslines;
    Names *lines;
    struct busstop *next;
} BusStop;

typedef struct links{
    double cost;
    double duration;
    struct links *next;
}Link;

typedef struct busline {
    char *name;
    char *origin_stop;
    char *destiny_stop;
    int stops_number;
    double cost;
    double duration;
    Names *stops;
    Names *stop_inv;
    Link *links;
    struct busline *next;
} BusLines;

void NoMemory(BusStop *headstop, BusLines *headline, char *cmd);


/*c*/
int compare_line(BusLines *head, char *name);
int verify_inverso();
void list_busline_stops(BusLines *head, char *name);
void list_busline_stops_inverted(BusLines *head, char *name);
void list_busline(BusLines *head);

/*p*/
int compare_stop(BusStop *head, char *name);
char remove_quotes(char name[]);
void list_busstops(BusStop *head);
int manage_p(int already_ext, BusStop *head, char *name_s);

/*l*/
int verify_link(BusStop *headstop ,BusLines *headline,char *cmd ,char *s_name_d, char *s_name_o, char *l_name, double cost, double duration);
Names* create_new_name(BusStop *headstop ,BusLines *headline,char *cmd );
Link* create_new_link(double cost, double duration,BusStop *headstop ,BusLines *headline,char *cmd );

/*r*/
Names* remove_line_from_stop(BusStop *headS,Names *head, char *l_name);
void free_line(BusLines *line);

/*e*/
void remove_stop_from_line(BusLines *headL ,char *s_name);
void free_stop(BusStop *stop);


#endif