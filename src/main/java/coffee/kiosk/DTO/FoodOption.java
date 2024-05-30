package coffee.kiosk.DTO;

public class FoodOption {
    int optionId;
    int menuId;
    int hot;                    // 1: 선택 / 0: 선택 안함 (Default = 0)
    int ice;                    // 얼음양 1: 기본 / 2: 적게 / 3: 많이
    int shot;                   // 1: 기본 / 2: 연하게 / 3: 2샷
    boolean steviaFlag;         // Default = F
    boolean syrupFlag;          // Default = F
    boolean decaffeineFlag;     // Default = F


    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getIce() {
        return ice;
    }

    public void setIce(int ice) {
        this.ice = ice;
    }

    public int getShot() {
        return shot;
    }

    public void setShot(int shot) {
        this.shot = shot;
    }

    public boolean isSteviaFlag() {
        return steviaFlag;
    }

    public void setSteviaFlag(boolean steviaFlag) {
        this.steviaFlag = steviaFlag;
    }

    public boolean isSyrupFlag() {
        return syrupFlag;
    }

    public void setSyrupFlag(boolean syrupFlag) {
        this.syrupFlag = syrupFlag;
    }

    public boolean isDecaffeineFlag() {
        return decaffeineFlag;
    }

    public void setDecaffeineFlag(boolean decaffeineFlag) {
        this.decaffeineFlag = decaffeineFlag;
    }
}
