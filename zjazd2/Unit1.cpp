//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "Unit1.h"
#include "Unit3.h"
#include <math.h>
#include <cstdio.h>
#include <iostream.h>
#include <string>
#include <sstream>
//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma resource "*.dfm"
TForm1 *Form1;
//---------------------------------------------------------------------------
__fastcall TForm1::TForm1(TComponent* Owner)
	: TForm(Owner)
{
	StringGrid1->Cells[0][0] = "X";
	StringGrid1->Cells[1][0] = "Y";
	StringGrid1->Cells[2][0] = "Waga";
	liczbaWezlow = 0;
	wspolrzedne = new Wspolrzedna[200];
	rozmiarPedzla = 1;
	domyslnaWaga = 1;
	kolor = clBlack;
}
//---------------------------------------------------------------------------

void __fastcall TForm1::MainMenuZamknijClick(TObject *Sender)
{
	Close();
}
//---------------------------------------------------------------------------
void TForm1::dodajPunkt(double x, double y, double waga)
{
	liczbaWezlow++;
	if(liczbaWezlow == StringGrid1->RowCount)
	{
		StringGrid1->RowCount++;
	}
	StringGrid1->Cells[0][liczbaWezlow] = x;
	StringGrid1->Cells[1][liczbaWezlow] = y;
	StringGrid1->Cells[2][liczbaWezlow] = waga;
	rysuj();
}
//---------------------------------------------------------------------------
void  TForm1::usunPunkt(double x, double y)
{
	bool punktZnaleziony = false;
	if(liczbaWezlow > 0)
	{
		for(int i = 1; i <= liczbaWezlow; i++)
		{
			if(abs(StrToFloat(StringGrid1->Cells[0][i]) - x) < 10 &&
			 abs(StrToFloat(StringGrid1->Cells[1][i]) - y) < 10 && !punktZnaleziony)
			{
				punktZnaleziony = true;

			}
			if(punktZnaleziony && i <liczbaWezlow)
			{
				StringGrid1->Cells[0][i] = StringGrid1->Cells[0][i+1];
				StringGrid1->Cells[1][i] = StringGrid1->Cells[1][i+1];
				StringGrid1->Cells[2][i] = StringGrid1->Cells[2][i+1];
			}
			else if(punktZnaleziony && i == liczbaWezlow)
			{
				StringGrid1->Cells[0][liczbaWezlow] = NULL;
				StringGrid1->Cells[1][liczbaWezlow] = NULL;
				StringGrid1->Cells[2][liczbaWezlow] = NULL;
				StringGrid1->RowCount--;
				liczbaWezlow--;
            }
		}
	}
	if(punktZnaleziony)
	{
		rysuj();
    }
}

//---------------------------------------------------------------------------

void __fastcall TForm1::ToolButton1Click(TObject *Sender)
{
	Form4->ShowModal();
	double x = StrToFloat(Form4->EditX->Text);
	double y = StrToFloat(Form4->EditY->Text);
	double waga = StrToFloat(Form4->EditWaga->Text);
	Form4->Close();
	dodajPunkt(x, y, waga);
}
//---------------------------------------------------------------------------

void __fastcall TForm1::ToolButton2Click(TObject *Sender)
{
	for(int i = liczbaWezlow; i > 0; i--)
	{
		usunPunkt(StrToFloat(StringGrid1->Cells[0][i]), StrToFloat(StringGrid1->Cells[1][i]));
	}
	rysuj();
}
//---------------------------------------------------------------------------
void TForm1::kopiujDoTablicy()
{
	wezly = new SWezel[liczbaWezlow];
	for(int i = 1; i <= liczbaWezlow; i++)
	{
		wezly[i-1].x = StrToFloat(StringGrid1->Cells[0][i]);
		wezly[i-1].y = StrToFloat(StringGrid1->Cells[1][i]);
		wezly[i-1].waga = StrToFloat(StringGrid1->Cells[2][i]);
	}
}
//---------------------------------------------------------------------------
void TForm1::rysuj()
{
	wymazIRysuj();
	PaintBox1->Canvas->Pen->Width = rozmiarPedzla;
	PaintBox1->Canvas->Pen->Color = kolor;
	Form3->RysujKrzywa(wezly, liczbaWezlow, PaintBox1->Canvas, wspolrzedne);
	//delete(wezly);
}

//---------------------------------------------------------------------------

void TForm1::rysujPunkt(int x, int y)
{
	PaintBox1->Canvas->Pen->Width = 5;
	PaintBox1->Canvas->Pen->Color = clRed;
	PaintBox1->Canvas->Ellipse(x, y, x+1, y+1);
}

//---------------------------------------------------------------------------

void TForm1::wymazIRysuj()
{
    PaintBox1->Canvas->Brush->Style = bsClear;
	PaintBox1->Repaint();
	kopiujDoTablicy();
	for(int i = 0; i < liczbaWezlow; i++)
	{
		rysujPunkt(wezly[i].x, wezly[i].y);
	}
}

//---------------------------------------------------------------------------

void __fastcall TForm1::ToolButton3Click(TObject *Sender)
{
	zapiszDoPliku();
}
//---------------------------------------------------------------------------

void __fastcall TForm1::PaintBox1MouseDown(TObject *Sender, TMouseButton Button, TShiftState Shift,
          int X, int Y)
{
	if(Shift.Contains(ssRight))
	{
		dodajPunkt(X, Y, domyslnaWaga);
	}
	else if(Shift.Contains(ssLeft))
	{
		usunPunkt(X, Y);
	}
}
//---------------------------------------------------------------------------

void TForm1::zapiszDoPliku()
{
	SaveDialog1->Execute();
	char *nazwaPliku = AnsiString(SaveDialog1->FileName).c_str();
	freopen(nazwaPliku, "w", stdout);
	for (int i = 0; i < 200; i++)
	{
		cout << wspolrzedne[i].x << "\t" << wspolrzedne[i].y << endl;
	}
	fclose(stdout);
}

//---------------------------------------------------------------------------

void __fastcall TForm1::PaintBox1MouseMove(TObject *Sender, TShiftState Shift, int X,
          int Y)
{
	String polozenieX = static_cast<String>(X);
	String polozenieY = static_cast<String>(Y);
	StatusBar1->Panels->operator[](0)->Text = "(" + polozenieX + ";" + polozenieY + ")";
}
//---------------------------------------------------------------------------

void __fastcall TForm1::MainMenuNowyClick(TObject *Sender)
{
	zapiszDoPliku();
}
//---------------------------------------------------------------------------

void __fastcall TForm1::MainMenuDodajClick(TObject *Sender)
{
	Form4->ShowModal();
	double x = StrToFloat(Form4->EditX->Text);
	double y = StrToFloat(Form4->EditY->Text);
	double waga = StrToFloat(Form4->EditWaga->Text);
	Form4->Close();
	dodajPunkt(x, y, waga);
}
//---------------------------------------------------------------------------

void __fastcall TForm1::MainMenuOpcjeClick(TObject *Sender)
{
	Form2->ShowModal();
	domyslnaWaga = StrToFloat(Form2->Edit1->Text);
	rozmiarPedzla = StrToFloat(Form2->Edit2->Text);
	String kodKoloru = Form2->ComboBox1->Text;
	if(kodKoloru == "Bia³y")
	{
		kolor = clWhite;
	}
	else if(kodKoloru == "Czarny")
	{
		kolor = clBlack;
	}
	else if(kodKoloru == "Czerwony")
	{
		kolor = clRed;
	}
	else if(kodKoloru == "Niebieski")
	{
		kolor = clBlue;
	}
	else if(kodKoloru == "Zielony")
	{
		kolor = clGreen;
	}
	else if(kodKoloru == "¯ó³ty")
	{
		kolor = clYellow;
	}
	else
	{
		kolor = clBlack;
	}
	Form2->Close();
}

//---------------------------------------------------------------------------

void __fastcall TForm1::MainMenuOtworzClick(TObject *Sender)
{
	OpenTextFileDialog1->Execute();
	char *nazwaPliku = AnsiString(OpenTextFileDialog1->FileName).c_str();
	freopen(nazwaPliku, "r", stdin);
	cin >> wspolrzedne[0].x;
	cin >> wspolrzedne[0].y;
	PaintBox1->Canvas->MoveTo(wspolrzedne[0].x, wspolrzedne[0].y);
	for (int i = 1; i < 200; i++)
	{
		cin >> wspolrzedne[i].x;
		cin >> wspolrzedne[i].y;
		PaintBox1->Canvas->LineTo(wspolrzedne[i].x, wspolrzedne[i].y);
	}
	fclose(stdin);
}
//---------------------------------------------------------------------------

void __fastcall TForm1::Zapiszrysunek1Click(TObject *Sender)
{
	tlo = new TBitmap;
	tlo->Width = PaintBox1->Width;
	tlo->Height = PaintBox1->Height;
	Form3->RysujKrzywa(wezly, liczbaWezlow, tlo->Canvas, wspolrzedne);
	if(SaveDialog1->Execute())
	{
		tlo->SaveToFile(SaveDialog1->FileName);
	}
}
//---------------------------------------------------------------------------
