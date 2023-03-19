package Hammurabi;
import java.io.IOException;
import java.util.*;

public class Hammurabi {
	Random rand = new Random();
	Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		new Hammurabi().playGame();
	}

	void playGame() {
		int grainTotal = 3000;
		int population = 100;
		int currentYear = 1;
		int immigrants = 5;
		int starvedPopulation = 0;
		int plagueDeaths = 0;
		int acres = 1000;
		int bushelsPerAcre = 3;
		int grainEatenByRats = 200;
		int landValue = 19;
		int grain = grainTotal - grainEatenByRats;

		while (currentYear < 10) {
			printSummary(currentYear, starvedPopulation, immigrants, population, grain, grainTotal, bushelsPerAcre, grainEatenByRats, acres, landValue);
			int acresBought = askHowManyAcresToBuy(landValue, grain);
				acres += acresBought;
				grain -= acresBought * landValue;
			int acresSold = askHowManyAcresToSell(acres);
				acres -= acresSold;
				grain += acresSold * landValue;
			int food = askHowMuchGrainToFeedPeople(grain);
				grain -= food;
			int plantedAcres = askHowManyAcresToPlant(acres, population, grain);
				grain += plantedAcres * 3;
			plagueDeaths += plagueDeaths(population);
				population -= plagueDeaths;
			starvedPopulation = starvationDeaths(population, food);
				population -= starvedPopulation;
			currentYear++;
		}
		finalSummary();
	}

	public void printSummary(int currentYear, int starvedPopulation, int immigrants, int population, int grain, int grainTotal, int bushelsPerAcre, int grainEatenByRats, int acres, int landValue) {
		System.out.println("O great Hammurabi!\n" +
				"You are in year " + currentYear + " of your ten year rule.\n" +
				"In the previous year " + starvedPopulation + " people starved to death.\n" +
				"In the previous year " + immigrants + " people entered the kingdom.\n" +
				"The population is now " + population + ".\n" +
				"We harvested " + grainTotal + " bushels at " + bushelsPerAcre + " bushels per acre.\n" +
				"Rats destroyed " + grainEatenByRats + " bushels, leaving " + grain + " bushels in storage.\n" +
				"The city owns " + acres + " acres of land.\n" +
				"Land is currently worth " + landValue + " bushels per acre." );
	}

	public void finalSummary() {
		System.out.println();
	}

	int askHowManyAcresToBuy(int price, int bushels) {
		int maxAcresYouCanAfford = bushels / price;
		boolean cameCorrect = false;
		int acresBought = 0;
		while(!cameCorrect) {
			System.out.println("\nHow many acres of land would you like to buy?");
			int acresWanted = scanner.nextInt();
			if (acresWanted < maxAcresYouCanAfford) {
				acresBought += acresWanted;
				cameCorrect = true;
			} else {
				System.out.println("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels left!");
			}
		}
		return acresBought;
	}

	int askHowManyAcresToSell(int acresOwned) {
		boolean cameCorrect = false;
		int acresSold = 0;
		while(!cameCorrect) {
			System.out.println("\nHow many acres of land would you like to sell?");
			int acresYouWantToSell = scanner.nextInt();
			if (acresYouWantToSell < acresOwned) {
				acresSold += acresYouWantToSell;
				cameCorrect = true;
			} else {
				System.out.println("O Great Hammurabi, surely you jest! We have only " + acresOwned + " acres left!");
			}
		}
		return acresSold;
	}
	int askHowMuchGrainToFeedPeople(int bushels) {
		boolean cameCorrect = false;
		int grainForFood = 0;
		while (!cameCorrect) {
			System.out.println("\nHow grain would you like to use to feed the population with?");
			int edibleGrain = scanner.nextInt();
			if (edibleGrain < bushels) {
				grainForFood += edibleGrain;
				cameCorrect = true;
			} else {
				System.out.println("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels left!");
			}
		}
		return grainForFood;
	}

	int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
		boolean cameCorrect = false;
		int plantableAcresByWorkforce = population * 10;
		int plantableAcresByGrain = bushels / 2;
		int acresToPlant = 0;
		while (!cameCorrect) {
			System.out.println("\nHow many acres would you like to plant?");
			int acresDesiredToBePlanted = scanner.nextInt();
			if (acresDesiredToBePlanted > acresOwned) {
				System.out.println("O Great Hammurabi, surely you jest! We have only " + acresOwned + " acres left!");
			} else if (acresDesiredToBePlanted > plantableAcresByWorkforce) {
				System.out.println("O Great Hammurabi, surely you jest! We have only " + population + " people!");
			} else if (acresDesiredToBePlanted > plantableAcresByGrain) {
				System.out.println("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels left!");
			} else {
				acresToPlant += acresDesiredToBePlanted;
				cameCorrect = true;
			}
		}
		return acresToPlant;
	}


	int plagueDeaths(int population) {
		int deathPercent = rand.nextInt(100);
		int plagueDeaths = 0;
		if (deathPercent < 15) {
			plagueDeaths += population;
		}
		return plagueDeaths;
	}

	int starvationDeaths(int population, int bushelsFedToPeople) {
		int hungryFolks = bushelsFedToPeople / 20;
		int deadFolks = population - hungryFolks;
		return deadFolks;
	}

	boolean uprising(int population, int howManyPeopleStarved) {
		double boilingPoint = population * .45;
		boolean weRiot = false;
		if (howManyPeopleStarved > boilingPoint) {
			weRiot = true;
		}
		return weRiot;
	}

	int grainEatenByRats(int bushels) {
		int infestationPercent = rand.nextInt(100);
		int cropLossPercent = rand.nextInt(20) + 10;
		int cropLoss = 0;
		if (infestationPercent < 40) {
			cropLoss += bushels; //multiplied by cropLossPercent
		}
		return cropLoss;
	}

	int newCostOfLand() {
		//random number from 17 to 23
		return 0;
	}

	int harvest(int acres, int bushelsUsedAsSeed) {
		//random number between 1 and 6, inclusively
		return 0;
	}

	int newImmigrants(int pop, int acresOwned, int grain) {
		//starving
		return 0;
	}

}