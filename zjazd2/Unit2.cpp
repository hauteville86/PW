//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "Unit2.h"
//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma resource "*.dfm"
TForm2 *Form2;
//---------------------------------------------------------------------------
__fastcall TForm2::TForm2(TComponent* Owner)
	: TForm(Owner)
{
	ComboBox1->Items->Add("Niebieski");
	ComboBox1->Items->Add("Czerwony");
	ComboBox1->Items->Add("Zielony");
	ComboBox1->Items->Add("Żółty");
	ComboBox1->Items->Add("Czarny");
	ComboBox1->Items->Add("Biały");
}
//---------------------------------------------------------------------------
void __fastcall TForm2::Button1Click(TObject *Sender)
{
	Close();
}
//---------------------------------------------------------------------------
