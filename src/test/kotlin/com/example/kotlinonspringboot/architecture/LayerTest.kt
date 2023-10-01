package com.example.kotlinonspringboot.architecture

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes

@AnalyzeClasses(
    packages = ["com.example.kotlinonspringboot"],
    importOptions = [
        ImportOption.DoNotIncludeTests::class, ImportOption.DoNotIncludeJars::class
    ]
)
class LayerTest {

    companion object {
        private const val DOMAIN = "domain"
        private const val USECASE = "usecase"
        private const val PRESENTATION = "presentation"
        private const val INFRASTRUCTURE = "infrastructure"

        private const val DOMAIN_PACKAGE = "..domain.."
        private const val USECASE_PACKAGE = "..usecase.."
        private const val PRESENTATION_PACKAGE = "..presentation.."
        private const val INFRA_PACKAGE = "..infrastructure.."
    }

    @ArchTest
    fun t(importedClasses: JavaClasses){
        classes()
            .that()
            .resideInAPackage(USECASE_PACKAGE)
            .should()
            .onlyAccessClassesThat()
            .resideInAPackage(DOMAIN_PACKAGE)
            .check(importedClasses)




    }


    // @ArchTest
    // val `落ちるテスト「ドメイン層は、プレゼンテーション層、インフラ層、ユースケース層を参照する」` =
    //     classes()
    //         .that()
    //         .resideInAPackage(DOMAIN_PACKAGE)
    //         .should()
    //         .accessClassesThat()
    //         .resideInAnyPackage(USECASE_PACKAGE, PRESENTATION_PACKAGE, INFRA_PACKAGE)

//    @ArchTest
//    val `プレゼンテーション層はドメイン層とインフラ層を参照しない` =
//        noClasses()
//            .that()
//            .resideInAPackage(PRESENTATION_PACKAGE)
//            .should()
//            .accessClassesThat()
//            .resideInAnyPackage(INFRA_PACKAGE, DOMAIN_PACKAGE)
//
//    @ArchTest
//    val `ユースケース層はプレゼンテーション層を参照しない` =
//        noClasses()
//            .that()
//            .resideInAPackage(USECASE_PACKAGE)
//            .should()
//            .accessClassesThat()
//            .resideInAPackage(PRESENTATION_PACKAGE)

}