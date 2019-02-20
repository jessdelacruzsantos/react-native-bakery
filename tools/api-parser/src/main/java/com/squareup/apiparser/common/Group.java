package com.squareup.apiparser;
import static com.google.common.base.Preconditions.checkNotNull;

enum ReleaseStatus {
    EXCLUDED,
    INTERNAL,
    ALPHA,
    DEPRECATED,
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

    /**
    * return true if it is visible to customers - PUBLIC, ALPHA, BETA, DEPRECATED
    */
    public boolean isVisible() {
        return ReleaseStatus.ALPHA.ordinal() <= status.ordinal();
    }

    public boolean shouldIncludeStatus(ReleaseStatus statusDest) {
        // After introducing versioning, BETA, PUBLIC, and DEPRECATED will both be shown on the
        // API reference site while BETA/DEPRECATED fields will have a `beta`/`deprecated` on them.
        return statusDest.ordinal() >= status.ordinal() || statusDest == ReleaseStatus.BETA || statusDest == ReleaseStatus.DEPRECATED;
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
