package com.financial.analisys.expenses.api.gateways.mapimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financial.analisys.expenses.api.domain.Card;
import com.financial.analisys.expenses.api.domain.Category;
import com.financial.analisys.expenses.api.domain.Companion;
import com.financial.analisys.expenses.api.domain.Expense;
import com.financial.analisys.expenses.api.domain.User;
import com.financial.analisys.expenses.api.exceptions.TechnicalException;

public final class Repository {

	private static String USERS_REPOSITORY_NAME = "UsersData.json";
	private static String EXPENSES_REPOSITORY_NAME = "ExpensesData.json";
	private static String COMPANIONS_REPOSITORY_NAME = "CompanionsData.json";
	private static String CATEGORIES_REPOSITORY_NAME = "CategoriesData.json";
	private static String CARDS_REPOSITORY_NAME = "CardsData.json";
	private static String REPOSITORIES_PATH = "../resources/data/";
	private static String PROPERTIES_PATH = "../resources/repository.properties";
	private static final String USERS_REPOSITORY_NAME_PROPERTY = "repository.users.name";
	private static final String EXPENSES_REPOSITORY_NAME_PROPERTY = "repository.expenses.name";
	private static final String COMPANIONS_REPOSITORY_NAME_PROPERTY = "repository.companions.name";
	private static final String CATEGORIES_REPOSITORY_JSON_PROPERTY = "repository.categories.name";
	private static final String CARDS_REPOSITORY_NAME_PROPERTY = "repository.cards.name";
	private static final String REPOSITORIES_PATH_PROPERTY = "repository.name";

	private static Properties repositoryProperties;

	private Repository() {

	}

	protected static final Map<String, Card> cardsRepository;
	protected static final Map<String, Category> categoriesRepository;
	protected static final Map<String, Companion> companionsRepository;
	protected static final Map<String, Expense> expensesRepository;
	protected static final Map<String, User> usersRepository;

	static {
		loadProperties();
		cardsRepository = getMapRepository(CARDS_REPOSITORY_NAME,
				new TypeReference<Map<String, Card>>() {
				});
		categoriesRepository = getMapRepository(CATEGORIES_REPOSITORY_NAME,
				new TypeReference<Map<String, Category>>() {
				});
		companionsRepository = getMapRepository(CATEGORIES_REPOSITORY_NAME,
				new TypeReference<Map<String, Companion>>() {
				});
		expensesRepository = getMapRepository(EXPENSES_REPOSITORY_NAME,
				new TypeReference<Map<String, Expense>>() {
				});
		usersRepository = getMapRepository(USERS_REPOSITORY_NAME,
				new TypeReference<Map<String, User>>() {
				});
	}

	private static void loadProperties() {
		repositoryProperties = loadRepositoryProperties();
		setRepositoriesNames();
	}

	private static Properties loadRepositoryProperties() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File(PROPERTIES_PATH)));
			return properties;
		} catch (Exception e) {
			throw new TechnicalException(e);
		}
	}

	private static void setRepositoriesNames() {
		if (isPropertiesLoaded()) {
			USERS_REPOSITORY_NAME = repositoryProperties
					.getProperty(USERS_REPOSITORY_NAME_PROPERTY);
			EXPENSES_REPOSITORY_NAME = repositoryProperties
					.getProperty(EXPENSES_REPOSITORY_NAME_PROPERTY);
			COMPANIONS_REPOSITORY_NAME = repositoryProperties
					.getProperty(COMPANIONS_REPOSITORY_NAME_PROPERTY);
			CATEGORIES_REPOSITORY_NAME = repositoryProperties
					.getProperty(CATEGORIES_REPOSITORY_JSON_PROPERTY);
			CARDS_REPOSITORY_NAME = repositoryProperties
					.getProperty(CARDS_REPOSITORY_NAME_PROPERTY);
			REPOSITORIES_PATH = repositoryProperties
					.getProperty(REPOSITORIES_PATH_PROPERTY);
		}
	}

	private static boolean isPropertiesLoaded() {
		return repositoryProperties != null;
	}

	private static <T> Map<String, T> getMapRepository(String repositoryName,
			TypeReference<Map<String, T>> typeReference) {
		Map<String, T> map = null;
		try {
			if (fileExists(repositoryName)) {
				ObjectMapper mapper = new ObjectMapper();
				map = mapper.readValue(getFileDirectory(repositoryName),
						typeReference);
			} else {
				
				return new LinkedHashMap<>();
			}

		} catch (Exception e) {
			throw new TechnicalException(e);
		}

		return map;
	}

	private static boolean fileExists(String fileName) {
		return new File(REPOSITORIES_PATH + fileName).exists();
	}

	private static File getFileDirectory(String fileName) throws IOException {
		File file = new File(REPOSITORIES_PATH + fileName);
		file.getParentFile().mkdirs();
		if (!file.exists())
			file.createNewFile();
		return file;
	}

	static void saveRepositories() {
		saveCardsRepository();
		saveCategoriesRepository();
		saveCompanionsRepository();
		saveExpensesRepository();
		saveUsersRepository();
	}

	static void saveCardsRepository() {
		saveRepository(CARDS_REPOSITORY_NAME, cardsRepository);
	}

	static void saveCategoriesRepository() {
		saveRepository(CATEGORIES_REPOSITORY_NAME, categoriesRepository);
	}

	static void saveCompanionsRepository() {
		saveRepository(COMPANIONS_REPOSITORY_NAME, companionsRepository);
	}

	static void saveExpensesRepository() {
		saveRepository(EXPENSES_REPOSITORY_NAME, expensesRepository);
	}

	static void saveUsersRepository() {
		saveRepository(USERS_REPOSITORY_NAME, usersRepository);
	}

	private static <T> void saveRepository(String repositoryName,
			Map<String, T> repository) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(getFileDirectory(repositoryName), repository);
		} catch (Exception e) {
			throw new TechnicalException(
					"There is an isusse, the cards repository could not be saved",
					e);
		}
	}

	public static void setPropertiesPath(String path) {
		PROPERTIES_PATH = path;
		loadProperties();
	}
}
