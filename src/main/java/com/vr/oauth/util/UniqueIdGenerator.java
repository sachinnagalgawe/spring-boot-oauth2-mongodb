package com.vr.oauth.util;

import java.util.UUID;

/**
 * Created by sachin
 */
public final class UniqueIdGenerator {

  private UniqueIdGenerator() {
  }

  public static String generate() {
    return UUID.randomUUID().toString();
  }
}
