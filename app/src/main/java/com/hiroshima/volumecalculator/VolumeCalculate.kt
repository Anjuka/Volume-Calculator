package com.hiroshima.volumecalculator

import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Created by AnjukaK on 12/06/24.
 */

object VolumeCalculate {

    //cal the volume
    fun calVolume(viewType: Int, line1Val: Double, line2Val: Double, line3Val: Double): String {
        var volume = "0.0"
        when (viewType) {
            Constant.TYPE_SPHERE -> {
                volume = formatToTwoDecimalPlaces(calSphereVolume(line1Val))
            }

            Constant.TYPE_CONE -> {
                volume = formatToTwoDecimalPlaces(calConeVolume(line1Val, line2Val))
            }

            Constant.TYPE_CUBE -> {
                volume = formatToTwoDecimalPlaces(calCubeVolume(line1Val))
            }

            Constant.TYPE_CYLINDER -> {
                volume = formatToTwoDecimalPlaces(calCylinderVolume(line1Val, line2Val))
            }

            Constant.TYPE_RECTANGULAR -> {
                volume = formatToTwoDecimalPlaces(calRectangularVolume(line1Val, line2Val, line3Val))
            }

            Constant.TYPE_CAPSULE -> {
                volume = formatToTwoDecimalPlaces(calCapsuleVolume(line1Val, line2Val))
            }

            Constant.TYPE_SPHERICAL -> {
                volume = formatToTwoDecimalPlaces(calSphericalVolume(line1Val, line2Val, line3Val))
            }

            Constant.TYPE_CONICAL -> {
                volume = formatToTwoDecimalPlaces(calConicalVolume(line1Val, line2Val, line3Val))
            }

            Constant.TYPE_ELLIPSOID -> {
                volume = formatToTwoDecimalPlaces(calEllipsoidVolume(line1Val, line2Val, line3Val))
            }

            Constant.TYPE_PYRAMID -> {
                volume = formatToTwoDecimalPlaces(calPyramidVolume(line1Val, line2Val))
            }

            Constant.TYPE_TUBE -> {
                volume = formatToTwoDecimalPlaces(calTubeVolume(line1Val, line2Val, line3Val))
            }
        }

        return volume
    }

    private fun formatToTwoDecimalPlaces(value: Double): String {
        return String.format("%.2f", value)
    }

    //sphere volume
    private fun calSphereVolume(radius: Double): Double {
        return (4.0 / 3.0) * PI * radius.pow(3)
    }

    //cone volume
    private fun calConeVolume(baseRadius: Double, height: Double): Double {
        return (1.0 / 3.0) * PI * baseRadius.pow(2) * height
    }

    //cube volume
    private fun calCubeVolume(edgeLength: Double): Double {
        return edgeLength.pow(3)
    }

    //cylinder volume
    private fun calCylinderVolume(baseRadius: Double, height: Double): Double {
        return PI * baseRadius.pow(2) * height
    }

    //rectangular volume
    private fun calRectangularVolume(length: Double, width: Double, height: Double): Double {
        return length * width * height
    }

    //capsule volume
    private fun calCapsuleVolume(baseRadius: Double, height: Double): Double {
        return PI * baseRadius.pow(2) * (2 * baseRadius + height)
    }

    //spherical volume
    private fun calSphericalVolume(baseRadius: Double, ballRadius: Double, height: Double): Double {
        val capHeight = ballRadius - height
        val capBaseRadius = sqrt(ballRadius.pow(2) - capHeight.pow(2))
        return (1.0 / 3.0) * PI * height.pow(2) * (3 * baseRadius - height)
    }

    //conical volume
    private fun calConicalVolume(topRadius: Double, bottomRadius: Double, height: Double): Double {
        return return (1.0 / 3.0) * PI * height * (topRadius.pow(2) + bottomRadius.pow(2) + topRadius * bottomRadius)
    }

    //ellipsoid volume
    private fun calEllipsoidVolume(semiAxisA: Double, semiAxisB: Double, semiAxisC: Double): Double {
        return (4.0 / 3.0) * PI * semiAxisA * semiAxisB * semiAxisC
    }

    //pyramid volume
    private fun calPyramidVolume(baseEdge: Double, height: Double): Double {
        val baseArea = baseEdge * baseEdge
        return (1.0 / 3.0) * baseArea * height
    }

    //tube volume
    private fun calTubeVolume(outerRadius: Double, innerRadius: Double, height: Double) : Double {
        return PI * (outerRadius.pow(2) - innerRadius.pow(2)) * height
    }

}