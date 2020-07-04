package raytracer;

import java.util.ArrayList;
import java.util.List;

public class StackedTransform extends Transform implements ITransform {

    public Vec3D scale, rotation, translation;
    private List<Transform> transforms;

    public StackedTransform(Vec3D scale, Vec3D rotation, Vec3D translation) {
        super(scale, rotation, translation);
    }

    @Override
    public Vec3D getTranslation() {
        return translation;
    }

    @Override
    public void addTransform(Transform transform) {
        if (transforms == null) {
            transforms = new ArrayList<>();
        }
        transforms.add(transform);
    }
}
