package com.datapersistence;

import javax.json.JsonObject;

public interface JsonSerializable {
    JsonObject writeJson();
}
