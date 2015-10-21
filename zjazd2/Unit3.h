//---------------------------------------------------------------------------

#ifndef Unit3H
#define Unit3H
//---------------------------------------------------------------------------


class TForm3 : public TForm
{
	__published:	// IDE-managed Components
	
	private:	// User declarations
	public:		// User declarations
		int silnia(int n);
		float WielomianB(int i, int n, float u);
		void RysujKrzywa(struct SWezel *tablicaWezlow, int liczbaWezlow,
		TCanvas *Canvas, struct Wspolrzedna *wspolrzedne);
};

struct SWezel
{
 int waga, x, y;
 };

 struct Wspolrzedna
 {
	float x, y;
 };





#endif
