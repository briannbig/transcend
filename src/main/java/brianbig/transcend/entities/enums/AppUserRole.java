package brianbig.transcend.entities.enums;

import com.google.common.collect.Sets;

import java.util.Set;

import static brianbig.transcend.entities.enums.AppUserPermissions.*;

public enum AppUserRole {
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE)),
    STUDENT(Sets.newHashSet(STUDENT_READ)),
    DEVELOPER(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE));

    private final Set<AppUserPermissions> permissions;

    AppUserRole(Set<AppUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermissions> getPermissions() {
        return permissions;
    }
}
