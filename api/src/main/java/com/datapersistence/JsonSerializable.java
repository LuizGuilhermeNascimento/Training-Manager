package com.datapersistence;

import javax.json.JsonObject;
import java.util.UUID;

public interface JsonSerializable {
    JsonObject writeJson();

    UUID getId();
}
