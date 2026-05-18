cmake_minimum_required(VERSION 3.22.1)

project(elyasabdo3d)

add_library(
        elyasabdo3d
        SHARED
        native3d.cpp
)

find_library(
        log-lib
        log
)

target_link_libraries(
        elyasabdo3d
        ${log-lib}
)
