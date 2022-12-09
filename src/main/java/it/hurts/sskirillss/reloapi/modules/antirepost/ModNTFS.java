package it.hurts.sskirillss.reloapi.modules.antirepost;

import lombok.Getter;

public record ModNTFS(@Getter String referrerURL, @Getter String hostURL) {
    public boolean isFromCurseforge() {
        return referrerURL().contains("curseforge.com");
    }

    public boolean isFromModrinth() {
        return referrerURL().contains("modrinth.com");
    }
}