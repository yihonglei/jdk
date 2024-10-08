/*
 * Copyright (c) 2024, Red Hat, Inc.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 *
 */

#ifndef CGROUP_UTIL_LINUX_HPP
#define CGROUP_UTIL_LINUX_HPP

#include "utilities/globalDefinitions.hpp"
#include "cgroupSubsystem_linux.hpp"

class CgroupUtil: AllStatic {

  public:
    static int processor_count(CgroupCpuController* cpu, int host_cpus);
    // Given a memory controller, adjust its path to a point in the hierarchy
    // that represents the closest memory limit.
    static void adjust_controller(CgroupMemoryController* m);
    // Given a cpu controller, adjust its path to a point in the hierarchy
    // that represents the closest cpu limit.
    static void adjust_controller(CgroupCpuController* c);
};

#endif // CGROUP_UTIL_LINUX_HPP
