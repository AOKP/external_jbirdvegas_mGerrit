package com.jbirdvegas.mgerrit.search;

/*
 * Copyright (C) 2013 Android Open Kang Project (AOKP)
 *  Author: Evan Conway (P4R4N01D), 2013
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import com.jbirdvegas.mgerrit.database.UserChanges;
import com.jbirdvegas.mgerrit.objects.ServerVersion;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ProjectSearch extends SearchKeyword {

    public static final String OP_NAME = "project";

    static {
        registerKeyword(OP_NAME, ProjectSearch.class);
    }

    public ProjectSearch(String param) {
        super(OP_NAME, param);
    }

    @Override
    public String buildSearch() {
        // Exact match only
        return UserChanges.C_PROJECT + " = ?";
    }

    @Override
    public String getGerritQuery(ServerVersion serverVersion) {
        String param = getParam();
        // Keywords with empty parameters are ignored
        if (param == null || param.isEmpty()) return "";

        StringBuilder builder = new StringBuilder().append(OP_NAME).append(":\"");
        builder.append(param).append("\"");
        return builder.toString();
    }
}
