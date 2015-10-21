//---------------------------------------------------------------------------

#ifndef Unit1H
#define Unit1H
//---------------------------------------------------------------------------
#include <System.Classes.hpp>
#include <Vcl.Controls.hpp>
#include <Vcl.StdCtrls.hpp>
#include <Vcl.Forms.hpp>
#include <System.Actions.hpp>
#include <System.ImageList.hpp>
#include <Vcl.ActnList.hpp>
#include <Vcl.ComCtrls.hpp>
#include <Vcl.ExtCtrls.hpp>
#include <Vcl.Grids.hpp>
#include <Vcl.ImgList.hpp>
#include <Vcl.ToolWin.hpp>
#include <Vcl.Menus.hpp>
#include <Vcl.ActnCtrls.hpp>
#include <Vcl.ActnMan.hpp>
#include "Unit2.h"
#include "Unit3.h"
#include "Unit4.h"
#include <Vcl.Dialogs.hpp>
#include <Vcl.ExtDlgs.hpp>
//---------------------------------------------------------------------------
class TForm1 : public TForm
{
__published:	// IDE-managed Components
	TMainMenu *MainMenu1;
	TMenuItem *MainMenuPlik;
	TMenuItem *MainMenuEdycja;
	TStatusBar *StatusBar1;
	TPaintBox *PaintBox1;
	TStringGrid *StringGrid1;
	TToolBar *ToolBar1;
	TMenuItem *MainMenuNowy;
	TMenuItem *MainMenuOtworz;
	TMenuItem *N1;
	TMenuItem *MainMenuZamknij;
	TImageList *ImageList1;
	TToolButton *ToolButton1;
	TToolButton *ToolButton2;
	TToolButton *ToolButton3;
	TToolButton *ToolButton4;
	TActionList *ActionList1;
	TMenuItem *MainMenuDodaj;
	TMenuItem *MainMenuOpcje;
	TAction *Action1;
	TAction *Action2;
	TAction *Action3;
	TMenuItem *Zapiszrysunek1;
	TSaveDialog *SaveDialog1;
	TOpenTextFileDialog *OpenTextFileDialog1;
	void __fastcall MainMenuZamknijClick(TObject *Sender);
	void __fastcall ToolButton1Click(TObject *Sender);
	void __fastcall ToolButton2Click(TObject *Sender);
	void __fastcall ToolButton3Click(TObject *Sender);
	void __fastcall PaintBox1MouseDown(TObject *Sender, TMouseButton Button, TShiftState Shift,
          int X, int Y);
	void __fastcall PaintBox1MouseMove(TObject *Sender, TShiftState Shift, int X, int Y);
	void __fastcall MainMenuNowyClick(TObject *Sender);
	void __fastcall MainMenuDodajClick(TObject *Sender);
	void __fastcall MainMenuOpcjeClick(TObject *Sender);
	void __fastcall MainMenuOtworzClick(TObject *Sender);
	void __fastcall Zapiszrysunek1Click(TObject *Sender);

private:	// User declarations
	SWezel *wezly;
	Wspolrzedna *wspolrzedne;
	TBitmap *tlo;
	TColor kolor;
	int rozmiarPedzla;
	int domyslnaWaga;
	int liczbaWezlow;
	void dodajPunkt(double x, double y, double waga);
	void usunPunkt(double x, double y);
	void kopiujDoTablicy();
	void rysuj();
	void rysujPunkt(int x, int y);
	void wymazIRysuj();
	void zapiszDoPliku();
public:		// User declarations
	__fastcall TForm1(TComponent* Owner);
};
//---------------------------------------------------------------------------
extern PACKAGE TForm1 *Form1;
extern PACKAGE TForm2 *Form2;
extern PACKAGE TForm3 *Form3;
extern PACKAGE TForm4 *Form4;
//---------------------------------------------------------------------------
#endif
