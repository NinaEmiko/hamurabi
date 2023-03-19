

import java.util.*;

public class Hammurabi {
	Random rand = new Random();
	Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		new Hammurabi().playGame();
	}

	void playGame() {
		boolean gameOver = false;
		int grainHarvested = 3000;
		int population = 100;
		int currentYear = 1;
		int immigrants = 5;
		int starvedPopulation = 0;
		int plagueDeaths = 0;
		int acres = 1000;
		int bushelsPerAcre = 3;
		int grainEatenByRats = 200;
		int landValue = 19;
		int grain = grainHarvested - grainEatenByRats;

		int totalDeaths = 0;
		int totalStarvations = 0;
		int totalDeathsFromPlague = 0;
		int totalImmigrants = 0;

		while (currentYear < 10 && !gameOver) {
			printSummary(currentYear, plagueDeaths, starvedPopulation, immigrants, population, grain, grainHarvested, bushelsPerAcre, grainEatenByRats, acres, landValue);
			int acresBought = askHowManyAcresToBuy(landValue, grain);
				acres += acresBought;
				grain -= acresBought * landValue;
			display(population, grain, acres, landValue);
			int acresSold = askHowManyAcresToSell(acres);
				acres -= acresSold;
				grain += acresSold * landValue;
			display(population, grain, acres, landValue);
			int food = askHowMuchGrainToFeedPeople(grain);
				grain -= food;
			display(population, grain, acres, landValue);
			int plantedAcres = askHowManyAcresToPlant(acres, population, grain);
				grain += plantedAcres * 3;
			plagueDeaths += plagueDeaths(population);
				population -= plagueDeaths;
				totalDeathsFromPlague += plagueDeaths;
				totalDeaths += plagueDeaths;
			starvedPopulation = starvationDeaths(population, food);
				population -= starvedPopulation;
				totalStarvations += starvedPopulation;
				totalDeaths += starvedPopulation;
			if (starvedPopulation < 1) {
				immigrants = newImmigrants(population, acres, grain);
				totalImmigrants += immigrants;
			}
			population += immigrants;
			landValue = newCostOfLand();
			grainHarvested = harvest(plantedAcres);
				bushelsPerAcre = grainHarvested / plantedAcres;
			grainEatenByRats -= grainEatenByRats(grain);
				grainEatenByRats -= grainEatenByRats;

			if (uprising(population, starvedPopulation)) {
				gameOver = true;
			}
			currentYear++;
		}
		finalSummary(totalDeaths, totalStarvations, totalDeathsFromPlague, totalImmigrants, population, acres);
		if (gameOver) {
			gameOver();
		}
	}

	public void printSummary(int currentYear, int plagueDeaths, int starvedPopulation, int immigrants, int population, int grain, int grainHarvested, int bushelsPerAcre, int grainEatenByRats, int acres, int landValue) {
		System.out.println("O great Hammurabi!\n" +
				"You are in year " + currentYear + " of your ten year rule.\n" +
				"In the previous year " + starvedPopulation + " people starved to death.\n" +
				"In the previous year " + plagueDeaths + " people died of the plague.\n" +
				"In the previous year " + immigrants + " people entered the kingdom.\n" +
				"The population is now " + population + ".\n" +
				"We harvested " + grainHarvested + " bushels at " + bushelsPerAcre + " bushels per acre.\n" +
				"Rats destroyed " + grainEatenByRats + " bushels, leaving " + grain + " bushels in storage.\n" +
				"The city owns " + acres + " acres of land.\n" +
				"Land is currently worth " + landValue + " bushels per acre." );
	}

	public void finalSummary(int totalDeaths, int deathsFromStarvation, int deathsFromThePlague, int totalImmigrants, int population, int acres) {
		System.out.println("\nCongratulations!! You have successfully completed your term!\n" +
				"During your term you were responsible for:\n" +
				"Deaths: " + totalDeaths + "\n" +
				"Deaths from starvation: " + deathsFromStarvation + "\n" +
				"Deaths from the plague: " + deathsFromThePlague + "\n" +
				"Successful immigrations: " + totalImmigrants + "\n" +
				"Total population: " + population + "\n" +
				"Acres of land per person: " + acres / population);
	}
	public void gameOver() {
		System.out.println("You have been removed from office. If I were you, I'd slip out in the middle of the night.");
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
			System.out.println("\nHow much grain would you like to use to feed the population with?");
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
			plagueDeaths = population / 2;
		}
		return plagueDeaths;
	}

	int starvationDeaths(int population, int bushelsFedToPeople) {
		int deadFolks = 0;
		if (bushelsFedToPeople < population * 20) {
			int hungryFolks = bushelsFedToPeople / 20;
			deadFolks = population - hungryFolks;
		}
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
		double cropLossPercent = (rand.nextInt(20) + 10);
		int cropLoss = 0;
		if (infestationPercent < 40) {
			cropLoss += bushels * (cropLossPercent / 100);
		}
		return cropLoss;
	}

	int newCostOfLand() {
		int landCost = rand.nextInt(7) + 17;
		return landCost;
	}

	int harvest(int bushelsUsedAsSeed) {
		int newHarvest = rand.nextInt(6) + 1;
		int roi = newHarvest * bushelsUsedAsSeed;
		return roi;
	}

	int newImmigrants(int population, int acresOwned, int grain) {
		int newPeeps = (20 * acresOwned + grain) / (100 * population) + 1;
		return newPeeps;
	}

	void display(int population, int grain, int acres, int landValue) {
		System.out.println("\nCurrent Population: " + population + " | Grain in Storage: " + grain + " | Land Owned: " + acres + " | Land Value: " + landValue);
	}
}