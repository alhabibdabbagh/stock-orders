package com.broker.stockorders.dto.response;

public class AssetResponse {
    private Long id;
    private String assetName;   // Example: AAPL, TSLA, or TRY
    private Long size;          // Total owned
    private Long usableSize;    // Usable for trading

    public AssetResponse(Long id, String assetName, Long size, Long usableSize) {
        this.id = id;
        this.assetName = assetName;
        this.size = size;
        this.usableSize = usableSize;
    }

    public AssetResponse() {
    }

    public static AssetResponseBuilder builder() {
        return new AssetResponseBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getAssetName() {
        return this.assetName;
    }

    public Long getSize() {
        return this.size;
    }

    public Long getUsableSize() {
        return this.usableSize;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setUsableSize(Long usableSize) {
        this.usableSize = usableSize;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AssetResponse)) return false;
        final AssetResponse other = (AssetResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$assetName = this.getAssetName();
        final Object other$assetName = other.getAssetName();
        if (this$assetName == null ? other$assetName != null : !this$assetName.equals(other$assetName)) return false;
        final Object this$size = this.getSize();
        final Object other$size = other.getSize();
        if (this$size == null ? other$size != null : !this$size.equals(other$size)) return false;
        final Object this$usableSize = this.getUsableSize();
        final Object other$usableSize = other.getUsableSize();
        if (this$usableSize == null ? other$usableSize != null : !this$usableSize.equals(other$usableSize))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AssetResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $assetName = this.getAssetName();
        result = result * PRIME + ($assetName == null ? 43 : $assetName.hashCode());
        final Object $size = this.getSize();
        result = result * PRIME + ($size == null ? 43 : $size.hashCode());
        final Object $usableSize = this.getUsableSize();
        result = result * PRIME + ($usableSize == null ? 43 : $usableSize.hashCode());
        return result;
    }

    public String toString() {
        return "AssetResponse(id=" + this.getId() + ", assetName=" + this.getAssetName() + ", size=" + this.getSize() + ", usableSize=" + this.getUsableSize() + ")";
    }

    public static class AssetResponseBuilder {
        private Long id;
        private String assetName;
        private Long size;
        private Long usableSize;

        AssetResponseBuilder() {
        }

        public AssetResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AssetResponseBuilder assetName(String assetName) {
            this.assetName = assetName;
            return this;
        }

        public AssetResponseBuilder size(Long size) {
            this.size = size;
            return this;
        }

        public AssetResponseBuilder usableSize(Long usableSize) {
            this.usableSize = usableSize;
            return this;
        }

        public AssetResponse build() {
            return new AssetResponse(this.id, this.assetName, this.size, this.usableSize);
        }

        public String toString() {
            return "AssetResponse.AssetResponseBuilder(id=" + this.id + ", assetName=" + this.assetName + ", size=" + this.size + ", usableSize=" + this.usableSize + ")";
        }
    }
}
