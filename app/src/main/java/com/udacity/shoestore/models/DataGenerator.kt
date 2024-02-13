package com.udacity.shoestore.models

import android.content.Context
import kotlin.math.round
import kotlin.random.Random

class DataGenerator() {

    companion object{
        fun getData(): MutableList<Shoe> {
            val mList = mutableListOf<Shoe>()

            for (i in 0..10) {
                val name = shoeNames[Random.nextInt(shoeNames.size)]
                val size = round(Random.nextDouble(5.0, 15.0))
                val company = shoeCompanies[Random.nextInt(shoeCompanies.size)]
                val description = descriptions[Random.nextInt(descriptions.size)]

                val imageNum = Random.nextInt(1, 78)
                val imageName = if (imageNum < 10) "image00$imageNum.png" else "image0$imageNum.png"
                // val resource: Int = context.resources.getIdentifier(name, "drawable", "com.udacity.shoestore")

                val shoe = Shoe(name, size, company, description, mutableListOf(imageName))

                mList.add(shoe)
            }

            return mList
        }

        val shoeCompanies = listOf(
            "StrideRite",
            "Nike",
            "Adidas",
            "Reebok",
            "Puma",
            "New Balance",
            "Under Armour",
            "Vans",
            "Converse",
            "Skechers"
        )

        private val shoeNames = listOf(
            "Midnight Black Leather Loafers",
            "Crimson High-Top Sneakers",
            "Suede Boho Ankle Boots",
            "Sunrise Platform Sandals",
            "Arctic Frost Winter Boots",
            "Classic White Canvas Sneakers",
            "Velvet Night Party Heels",
            "Ocean Blue Slip-Ons",
            "Golden Glitter Ballet Flats",
            "Rustic Brown Hiking Boots",
            "Moonlight Grey Running Shoes",
            "Ivory Lace Wedding Pumps",
            "Emerald Green Velvet Mules",
            "Silver Sparkle Evening Sandals",
            "Coral Reef Flip Flops",
            "Chocolate Brown Oxford Shoes",
            "Platinum Metallic Sneakers",
            "Cherry Blossom Pink Espadrilles",
            "Cobalt Blue Dress Sandals",
            "Marble Print Fashion Sneakers",
            "Mocha Latte Suede Loafers",
            "Ruby Red Stiletto Heels",
            "Tropical Floral Slides",
            "Charcoal Grey Chelsea Boots",
            "Sunset Orange Slingbacks",
            "Navy Blue Boat Shoes",
            "Pine Forest Camo Hiking Boots",
            "Amber Sunset Sandals",
            "Bronze Shimmer Ballet Flats",
            "Indigo Denim Slip-On Sneakers"
        )
        private val descriptions: List<String> = listOf("Stride confidently in these sleek black leather loafers, perfect for both formal occasions and casual outings.",
            "Elevate your street style with these vibrant red high-top sneakers, featuring a comfortable cushioned sole for all-day wear.",
            "Step into luxury with these elegant suede ankle boots, adorned with delicate fringe detailing for a touch of bohemian charm.",
            "Make a statement with these bold platform sandals, featuring chunky straps and a vibrant floral pattern that's sure to turn heads.",
            "Stay cozy and chic in these faux fur-lined winter boots, designed to keep your feet warm and stylish during cold weather adventures.")


    }
}