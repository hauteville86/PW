#Wykorzystując program awk, napisać skrypt służący do zamiany fragmentów tekstu w
#pliku wejściowym.
#Postać wywołania skryptu:
#
# nazwa_skryptu  tekst1  tekst2  plik
#
#Wszystkie wystąpienia tekstu tekst1 w pliku powinny być zastąpione przez tekst2.
#Skrypt powinien zawierać obsługę błędów:
#- sygnalizować błędy składni (podając poprawną postać),
#- sygnalizować użycie niepoprawnego argumentu (np. nie istniejący plik),
#- sygnalizować brak odpowiednich praw dostępu.
if [ $# -lt 3 ]
then
    echo -e "Błąd składni. Poprawna postać to  nazwa_skryptu  tekst1  tekst2  plik\n"
else
    tekst1=$1
    tekst2=$2
    nazwa_pliku=$3
    if [ -e $nazwa_pliku  ]
    then
	if [ -w $nazwa_pliku ]
	then
	    awk '{ gsub("'$tekst1'", "'$tekst2'"); print}' $nazwa_pliku > tmp.txt
	    mv tmp.txt $nazwa_pliku
	else
	    echo -e "Plik nie ma możliwości zapisu.\n"
	    echo -e "Użyj opcji chmod +w {nazwa_pliku} w celu zmiany praw dostępu\n"
	fi
    else
	echo -e "Plik o nazwie %s nie istnieje.", $nazwa_pliku
    fi
fi
exit 0
