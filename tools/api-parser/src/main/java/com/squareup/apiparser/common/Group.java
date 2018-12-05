package com.squareup.apiparser;
import static com.google.common.base.Preconditions.checkNotNull;

enum ReleaseStatus {
    DEPRECATED,
    EXCLUDED,
    INTERNAL,
    ALPHA,
    BETA,
    PUBLIC;
}

class Group {
    public ReleaseStatus status;
    public String namespace;

    public Group(){
        this.namespace = "";
        this.status = ReleaseStatus.PUBLIC;
    }

    public Group(ReleaseStatus status, String namespace){
        this.namespace = namespace;
        this.status = status;
    }

    public boolean isCustomerFacing() {
        return ReleaseStatus.ALPHA.ordinal() <= status.ordinal();
    }

    public boolean shouldIncludeStatus(ReleaseStatus statusDest) {
        // After introducing versioning, BETA and PUBLIC will both be shown on the
        // API reference site while BETA fields will have a `beta` tag on them.
        return statusDest.ordinal() >= status.ordinal() || statusDest == ReleaseStatus.BETA;
    }

    // Takes namespace and status into account
    public boolean shouldInclude(Group group) {
        checkNotNull(group);
        if(status != ReleaseStatus.ALPHA){
            return shouldIncludeStatus(group.status);
        }
        else{
           return shouldIncludeStatus(group.status) && shouldIncludeAlphaNamespace(group);
        }
    }

    // Assuming current group is ALPHA
    public boolean shouldIncludeAlphaNamespace(Group group){
        checkNotNull(group);
        return group.status != ReleaseStatus.ALPHA || namespace.equals("") || namespace.equals(group.namespace);
    }
}
