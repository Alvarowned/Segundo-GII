#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <unistd.h>
#include <dirent.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "parser.h"

void mipwd(orden *O);
void mils(orden *O);
void mimkdir(orden *O);
void mirmdir(orden *O);
void micd(orden *O, char *path);
void micat(orden *O);
void micp(orden *O);
void mirm(orden *O);