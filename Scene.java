package raytracer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Scene {

    private final List<ParsedElement> elements = new ArrayList<>();
    private final List<Shape> shapes = new ArrayList<>();

    /**
     * Constructs an empty scene.
     */
    public Scene(){
    }


    /**
     * Populates a list of parsed scene elements
     *
     * @param element
     *          The parsed element.
     */
    public void addElement(ParsedElement element){
        elements.add(element);
    }


    /**
     * Returns a list of shapes.
     */
    public List<Shape> getShape(){
        for(ParsedElement element: elements){
            //System.out.println(element.toString()); // <-----uncomment debug point
            Shape shape = new ShapeFactory(element).getShape();
            if(shape != null ){
                shapes.add(shape);
            }
//            if(element.elementName.equals("Sphere")){
//                createSphere(element);
//            }
//            else if(element.elementName.equals("Cone")){
//                createCone(element);
//            }
        }
        return this.shapes;
    }


    /**
     * Creates a cone.
     * @param element
     *          The cone element.
     */
    private void createCone(ParsedElement element) {
        //Cone cone = new Cone ();
    }


    /**
     * Creates a sphere.
     * @param element
     *          The sphere element.
     */
    private void createSphere(ParsedElement element) {
        Vec3D center = new Vec3D(0, 0, 0);
        double radius = 1.0;
        Color diffuseColor;
        Material material = null; //todo: <-- bug

        for (String key : element.attributes.get(0).keySet()) {
            if(key.equals("diffuseColor")) {
                diffuseColor = toDiffuseColor(element.attributes.get(0).get("diffuseColor"));
                material = new Material(diffuseColor);
            }
            else if (key.equals("radius")) {
                radius = Double.parseDouble(element.attributes.get(0).get("radius"));
            }
            else if (key.equals("center")) {
                center = toVec3D(element.attributes.get(0).get("center"));
            }
        }
        Geometry sphere = new Sphere(center, radius);
        Appearance appearance = new Appearance(material);
        shapes.add(new Shape(sphere, appearance));
    }


    private Color toDiffuseColor(String str){
        int colorVal = Integer.parseInt(str.replaceAll("\\s+",""));
        return new Color(colorVal);
    }


    private Vec3D toVec3D(String str){
        String[] charValues = str.split("\\s+");
        return new Vec3D(Double.parseDouble(charValues[0]),
                Double.parseDouble(charValues[1]), Double.parseDouble(charValues[2]));
    }
}
