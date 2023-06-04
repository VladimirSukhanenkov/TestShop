package com.example.springsecurity.services;

import com.example.springsecurity.models.Person;
import com.example.springsecurity.models.Product;
import com.example.springsecurity.repositories.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    // Подключаем встроенный класс для хэширования паролей
    private final PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person findByLogin(Person person) {
        Optional<Person> person_db = personRepository.findByLogin(person.getLogin());
        return person_db.orElse(null);
    }

    @Transactional
    public void register(Person person) {
        // Подключаем хэширование пароля при регистрации - получаем пароль из объекта person(с формы)
        // и с помощью метода encode хэшируем его пароль и сохраняем его в пользователе
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        //добавляем пользователя в репозиторий
        person.setRole ("ROLE_USER");
        personRepository.save(person);
    }

    public List<Person> findAllUsers() {
        return personRepository.findAll();
    }

    public Person findById(int id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.orElse(null);
    }

    // Метод позволяет обновить данные о пользователе
    // Т.к. мы передаём ID, spring Data JPA сам понимает, что мы хотим обновить информацию о продукте
    @Transactional
    public void updatePerson (int id, Person person) {
        person.setId(id);
        personRepository.save(person);
    }
}
