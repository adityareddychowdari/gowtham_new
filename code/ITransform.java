package raytracer;

public interface ITransform {
    public Vec3D getTranslation();
    public void addTransform(Transform transform);
}
