//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop

#include "Unit3.h"
#include <math.h>
//---------------------------------------------------------------------------
#pragma package(smart_init)
//---------------------------------------------------------------------------
TForm3 *Form3;

int TForm3::silnia(int n)
{
    if (n == 0)
       return 1;
    return n * silnia(n - 1);
}

float TForm3::WielomianB(int i, int n, float u)
{
  if(i < 0 || i > n)
	return 0;
  else
  {
	if(i == 0)
		return pow(1-u, n);
	return silnia(n) * pow(u, i) * pow(1-u, n-i) / (silnia(i) * silnia(n-i));
  }
}
void TForm3::RysujKrzywa(SWezel *tablicaWezlow, int liczbaWezlow, TCanvas *Canvas, Wspolrzedna *wspolrzedne)
{
  float x, y;
//W tablicy musz¹ byæ co najmniej 3 punkty, aby móc narysowaæ krzyw¹.
  if(liczbaWezlow > 2)
  {
	Canvas->MoveTo(tablicaWezlow[0].x, tablicaWezlow[0].y);
	int indeksWspolrzednych = 0;

	for(float u = 0; u <= 1; u += 0.005)
	{
		float licznikX = 0;
		float licznikY = 0;
		float mianownik = 0;
		for(int i = 0; i < liczbaWezlow; i++)
		{
			licznikX += tablicaWezlow[i].waga*tablicaWezlow[i].x*
			WielomianB(i, liczbaWezlow-1, u);
			licznikY += tablicaWezlow[i].waga*tablicaWezlow[i].y*
			WielomianB(i, liczbaWezlow-1, u);
			mianownik += tablicaWezlow[i].waga*
			WielomianB(i, liczbaWezlow-1, u);
		}
		if(mianownik != 0)
		{
			x = licznikX/mianownik;
			y = licznikY/mianownik;
		}
		else
		{
			x = 0;
			y = 0;
		}
		Canvas->LineTo(x, y);
		//zapisywanie wspó³rzêdnych krzywej w osobnej tablicy w celu zapisu do pliku
		wspolrzedne[indeksWspolrzednych].x = x;
		wspolrzedne[indeksWspolrzednych].y = y;
		indeksWspolrzednych++;
    }
  }
}
