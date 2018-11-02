package com.squareup.apiparser;
class Namespace {
    public static boolean isMatched(ReleaseStatus releaseStatus, String namespaceSrc, String namespaceDest) {
    return releaseStatus != ReleaseStatus.ALPHA || namespaceSrc.equals("") || namespaceSrc.equals(namespaceDest);
    }
}
