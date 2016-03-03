#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char sigargs[32][80];

void sighandler(int);

int main()
{
        
	char bufor[80];
	char* arg[10];
	int bg;

	//Usunieta petla signal.
  	
	while (1)
	{
		printf("msh $ ");
		fgets(bufor, 80, stdin);
		bg = AnalizujPolecenie(bufor, arg);
		if (arg[0] == NULL)
			continue;
		else if (strcmp(arg[0], "exit")==0)
			exit(0);
		else if (strcmp(arg[0], "kill")==0)
		{
		      if (arg[1] != NULL && arg[2] != NULL)
		      {
			    int signum = atoi(arg[1]);
			    int pid = atoi(arg[2]);
			    kill(pid, signum);
		      }
		      else
		      {
			    printf("Blad kill\n");
		      }
		}
		else if (strcmp(arg[0], "trap")==0)
		{
		      if (arg[1] != NULL)
		      {
			    int signum = atoi(arg[1]);
			    if (signum > 0 && signum < 32)
			    {
				  int i = 1;
				  if(arg[2] != NULL)
				  {
				    strncpy(sigargs[signum], arg[i+1], strlen(arg[i+1])+1);
				    i++;
				    while (arg[i+1] != NULL && i < 9)
				      {
					strcat(sigargs[signum], " ");
					strcat(sigargs[signum], arg[i+1]);
					i++;
				      }
				    strcat(sigargs[signum], "\0");
				    signal(signum, sighandler);
				  }
				  else
				  {
				    signal(signum, SIG_DFL);
				  }				  
			    }
			    else
			    {
			      printf("Blad trap - signum\n");
			    }
		      }
		      else
		      {
			    printf("Blad trap\n");
		      }
		}
		else
			Wykonaj(arg, bg);
	}

}

int AnalizujPolecenie(char *bufor, char *arg[])
{
	int counter=0;
	int i=0, j=0;

	while (bufor[counter] != '\n')
	{
		while (bufor[counter] == ' ' || bufor[counter] == '\t')
                	counter++;
		if (bufor[counter] != '\n')
		{
			arg[i++] = &bufor[counter];
			while (bufor[counter] != ' ' && bufor[counter] != '\t' && bufor[counter] != '\n')
				counter++;
    	            if (bufor[counter] != '\n')
   	             	bufor[counter++] = '\0';
		}
	}
	bufor[counter] = '\0';
	arg[i]=NULL;
	if (i>0)
		while (arg[i-1][j] != '\0')
		{
			if (arg[i-1][j] == '&')
			{
				if (j == 0)
					arg[i-1] = NULL;
				else
					arg[i-1][j] = '\0';
				return 1;
			}
			j++;
		}
	return 0;
}


int Wykonaj(char **arg, int bg)
{
	pid_t pid;
	int status;

	if ((pid=fork()) == 0)
	{
		execvp(arg[0],arg);
		perror("Blad exec");
		exit(1);	}
	else if (pid > 0)
	{
		if (bg == 0)
			waitpid(pid, &status, 0);
		return 0;
	}
	else
	{
		perror("Blad fork");
		exit(2);
	}
}

void sighandler(int signum)
{
  pid_t pid;
      if (sigargs[signum][0] != NULL)
      {
        if((pid=fork()) == 0)
	  execlp("/bin/sh","/bin/sh","-c",sigargs[signum],NULL);
      }
      else
      {
	    signal(signum, SIG_DFL);
	    kill(getpid(), signum);
      }	 
}
