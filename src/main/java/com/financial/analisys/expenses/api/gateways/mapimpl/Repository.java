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

	public static final Map<String, Card> cardsRepository;
	public static final Map<String, Category> categoriesRepository;
	public static final Map<String, Companion> companionsRepository;
	public static final Map<String, Expense> expensesRepository;
	public static final Map<String, User> usersRepository;

	static {
		cardsRepository = loadCardsRepository();
		categoriesRepository = loadCategoriesRepository();
		companionsRepository = loadCompanionsRepository();
		expensesRepository = loadExpensesRepository();
		usersRepository = loadUsersRepository();
		loadProperties();
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
			return null;
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

	private static Map<String, Card> loadCardsRepository() {
		Map<String, Card> map;
		try {
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(getFileDirectory(CARDS_REPOSITORY_NAME),
					new TypeReference<Map<String, Card>>() {
					});

			if (map == null || map.isEmpty()) {
				return new LinkedHashMap<String, Card>();
			}

		} catch (Exception e) {
			return new LinkedHashMap<String, Card>();
		}

		return map;
	}

	private static Map<String, Category> loadCategoriesRepository() {
		Map<String, Category> map;
		try {
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(
					getFileDirectory(CATEGORIES_REPOSITORY_NAME),
					new TypeReference<Map<String, Category>>() {
					});

			if (map == null || map.isEmpty()) {
				return new LinkedHashMap<String, Category>();
			}

		} catch (Exception e) {
			return new LinkedHashMap<String, Category>();
		}

		return map;
	}

	private static Map<String, Companion> loadCompanionsRepository() {
		Map<String, Companion> map;
		try {
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(
					getFileDirectory(COMPANIONS_REPOSITORY_NAME),
					new TypeReference<Map<String, Companion>>() {
					});

			if (map == null || map.isEmpty()) {
				return new LinkedHashMap<String, Companion>();
			}

		} catch (Exception e) {
			return new LinkedHashMap<String, Companion>();
		}

		return map;
	}

	private static Map<String, Expense> loadExpensesRepository() {
		Map<String, Expense> map;
		try {
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(getFileDirectory(EXPENSES_REPOSITORY_NAME),
					new TypeReference<Map<String, Expense>>() {
					});

			if (map == null || map.isEmpty()) {
				return new LinkedHashMap<String, Expense>();
			}

		} catch (Exception e) {
			return new LinkedHashMap<String, Expense>();
		}

		return map;
	}

	private static Map<String, User> loadUsersRepository() {
		Map<String, User> map;
		try {
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(getFileDirectory(USERS_REPOSITORY_NAME),
					new TypeReference<Map<String, User>>() {
					});

			if (map == null || map.isEmpty()) {
				return new LinkedHashMap<String, User>();
			}

		} catch (Exception e) {
			return new LinkedHashMap<String, User>();
		}

		return map;
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
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(getFileDirectory(CARDS_REPOSITORY_NAME),
					cardsRepository);
		} catch (Exception e) {
			throw new TechnicalException(
					"There is an isusse, the cards repository could not be saved",
					e);
		}

	}

	static void saveCategoriesRepository() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(getFileDirectory(CATEGORIES_REPOSITORY_NAME),
					categoriesRepository);
		} catch (Exception e) {
			throw new RuntimeException(
					"There is an isusse, the categories repository could not be saved",
					e);
		}

	}

	static void saveCompanionsRepository() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(getFileDirectory(COMPANIONS_REPOSITORY_NAME),
					companionsRepository);
		} catch (Exception e) {
			throw new TechnicalException(
					"There is an isusse, the companions repository could not be saved",
					e);
		}

	}

	static void saveExpensesRepository() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(getFileDirectory(EXPENSES_REPOSITORY_NAME),
					expensesRepository);
		} catch (Exception e) {
			throw new TechnicalException(
					"There is an isusse, the expenses repository could not be saved",
					e);
		}

	}

	static void saveUsersRepository() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(getFileDirectory(USERS_REPOSITORY_NAME),
					usersRepository);
		} catch (Exception e) {
			throw new TechnicalException(
					"There is an isusse, the users repository could not be saved",
					e);
		}

	}

	public static void setPropertiesPath(String path) {
		PROPERTIES_PATH = path;
		loadProperties();
	}
}
