package raytracer;

import java.util.ArrayList;
import java.util.List;

public class Transform implements ITransform {

    public Vec3D scale, rotation, translation;
    private List<Transform> transforms;

    /**
     * Constructs a Transform.
     * @param scale
     *          The scale transform.
     * @param rotation
     *          The rotation transform.
     * @param translation
     *          The translation transform.
     */
    public Transform(Vec3D scale, Vec3D rotation, Vec3D translation) {
        this.scale = scale;
        this.rotation  = rotation;
        this.translation = translation;
    }

    @Override
    public Vec3D getTranslation() {
        return this.translation;
    }

    @Override
    public void addTransform(Transform transform) {
        if (transforms == null) {
            transforms = new ArrayList<>();
        }
        transforms.add(transform);
    }
}

