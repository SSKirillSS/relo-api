package it.hurts.sskirillss.reloapi.modules.antirepost;

import lombok.Getter;
import net.minecraftforge.fml.ModList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AntiRepost {
    @Getter
    private static final Map<String, ModNTFS> cachedNTFSData = new HashMap<>();

    public static ModNTFS getNTFSData(String modid) {
        if (!System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("windows"))
            return new ModNTFS("", "");

        if (getCachedNTFSData().containsKey(modid))
            return getCachedNTFSData().get(modid);

        List<String> data = new ArrayList<>();

        try {
            File file = new File(ModList.get().getModFileById(modid).getFile().getFilePath() + ":Zone.Identifier");

            try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
                data = bf.lines().collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String referrerURL = "";
        String hostURL = "";

        for (String entry : data) {
            String key = "ReferrerUrl=";

            if (referrerURL.isEmpty() && entry.startsWith(key))
                referrerURL = entry.replace(key, "");

            key = "HostUrl=";

            if (hostURL.isEmpty() && entry.startsWith(key))
                hostURL = entry.replace(key, "");
        }

        ModNTFS ntfs = new ModNTFS(referrerURL, hostURL);

        getCachedNTFSData().put(modid, ntfs);

        return ntfs;
    }
}