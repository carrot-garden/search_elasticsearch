/*
 * Licensed to Elastic Search and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.common.blobstore;

import org.elasticsearch.common.collect.ImmutableList;

import java.util.Iterator;

/**
 * @author kimchy (shay.banon)
 */
public class BlobPath implements Iterable<String> {

    private final ImmutableList<String> paths;

    public BlobPath() {
        this.paths = ImmutableList.of();
    }

    public static BlobPath cleanPath() {
        return new BlobPath();
    }

    private BlobPath(ImmutableList<String> paths) {
        this.paths = paths;
    }

    @Override public Iterator<String> iterator() {
        return paths.iterator();
    }

    public String[] toArray() {
        return paths.toArray(new String[paths.size()]);
    }

    public BlobPath add(String path) {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        return new BlobPath(builder.addAll(paths).add(path).build());
    }

    public String buildAsString(String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paths.size(); i++) {
            sb.append(paths.get(i));
            if (i < (paths.size() - 1)) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String path : paths) {
            sb.append('[').append(path).append(']');
        }
        return sb.toString();
    }
}