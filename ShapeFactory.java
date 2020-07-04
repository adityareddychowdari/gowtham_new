package raytracer;

import java.awt.*;

public class ShapeFactory {

    ParsedElement element;

    public ShapeFactory(ParsedElement element) {
        this.element=element;
    }

    public Shape createSphere(ParsedElement element) {
        Vec3D center = new Vec3D(0, 0, 0);
        double radius = 1.0;
        Color diffuseColor;
        Material material = null;

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
        return new Shape(sphere, appearance);
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

    public Shape createCone(ParsedElement element) {
        Vec3D center = new Vec3D(0, 0, 0);
        Vec3D vertex = new Vec3D(0, 0, 0);
        double radius = 1.0;
        Color diffuseColor;
        Material material = null;
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
            else if (key.equals("vertex")) {
                vertex = toVec3D(element.attributes.get(0).get("vertex"));
            }
        }
        //Geometry geometry = new Cone(this.apex, this.radius, this. height);
        Geometry cone = new Cone(radius, center, vertex);
        Appearance appearance = new Appearance(material);
        return new Shape(cone, appearance);
    }

    public Shape getShape() {
        Shape shape = null;
        if (element.elementName.equals("Sphere")) {
            shape=createSphere(element);
        } else if (element.elementName.equals("Cone")) {
            shape=createCone(element);
        }
        return shape;
    }

}
