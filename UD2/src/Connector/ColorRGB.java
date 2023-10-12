package Connector;
public class ColorRGB {

    public static ColorRGB getRed() {
		return RED;
	}
	public static ColorRGB getGreen() {
		return GREEN;
	}
	public static ColorRGB getBlue() {
		return BLUE;
	}
	public static ColorRGB getYellow() {
		return YELLOW;
	}
	public static ColorRGB getMagenta() {
		return MAGENTA;
	}
	public static ColorRGB getCyan() {
		return CYAN;
	}
	public static ColorRGB getBlanco() {
		return BLANCO;
	}
	public static ColorRGB getNegro() {
		return NEGRO;
	}
	public ColorRGB(int i, int j, int k) {
		// TODO Auto-generated constructor stub
	}
	public static final ColorRGB RED = new ColorRGB(255, 0, 0);
    public static final ColorRGB GREEN = new ColorRGB(0, 255, 0);
    public static final ColorRGB BLUE = new ColorRGB(0, 0, 255);
    public static final ColorRGB YELLOW = new ColorRGB(255, 255, 0);
    public static final ColorRGB MAGENTA = new ColorRGB(255, 0, 255);
    public static final ColorRGB CYAN = new ColorRGB(0, 255, 255);
    public static final ColorRGB BLANCO = new ColorRGB(255, 255, 255);
    public static final ColorRGB NEGRO = new ColorRGB(0, 0, 0);
    

}
