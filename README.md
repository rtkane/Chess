# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

## Modules

The application has three modules.

- **Client**: The command line program used to play a game of chess over the network.
- **Server**: The command line program that listens for network requests from the client and manages users and games.
- **Shared**: Code that is used by both the client and the server. This includes the rules of chess and tracking the state of a game.

## Starter Code

As you create your chess application you will move through specific phases of development. This starts with implementing the moves of chess and finishes with sending game moves over the network between your client and server. You will start each phase by copying course provided [starter-code](starter-code/) for that phase into the source code of the project. Do not copy a phases' starter code before you are ready to begin work on that phase.

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared test`      | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

## Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```






![alt text](https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDABLBoAmCtu+hx7ZhWqEUdPo0EwAIsDDAAgiBAoAzqswc5wAEbBVKGBx2ZM6MFACeq3ETQBzGAAYAdAE5M9qBACu2GADEaMBUljAASij2SKoWckgQaIEA7gAWSGBiiKikALQAfOSUNFAAXDAA2gAKAPJkACoAujAA9D4GUAA6aADeAETtlMEAtih9pX0wfQA0U7jqydAc45MzUyjDwEgIK1MAvpjCJTAFrOxclOX9g1AjYxNTs33zqotQyw9rfRtbO58HbE43FgpyOonKUCiMUyUAAFJForFKJEAI4+NRgACUh2KohOhVk8iUKnU5XsKDAAFUOrCbndsYTFMo1Kp8UYdOUyABRAAyXLg9RgdOAoxgADNvMMhR1MIziSyTqDcSpymgfAgEDiRCo2XLmaSYCBIXIUNTKLSOndZi83hxZj9tgztPL1GzjOUAJIAOW54UFwtG1v0ryW9s22xg3vqNWltDBOtOepJqnKRpQJoUPjAqQtQxFKCdRP1rNO7sjPq5ftjt3zs2AWdS9QgAGt0OXozB69nZc7i4rCvGUOUu42W+gtVQ8blToCLmUIlCkVBIqp1VhZ8D+0VqJcYNdLfnJuVVk8R03W2gj1N9hPKFvsuZygAmJxObr7vOjK8nqZnseXmBjxvdAOFMLxfH8AJoHYckYB5CBoiSAI0gyLJkHMNkjl3ao6iaVoDHUBI0HfAMUCDBYlgOLCQUKDdLg-Gsv0+J4bSWXY+gBc5NyVbUhxgBAEKQNBYXgxDUXRWJsUHXVe2TMkKTNOFSMLJlkzdDlyF5fl-QPUUJQgKUbh7Is1OnAdlT40iJynAlZJZVNjUyTNs1zRiC2M1SFVLDTvV9HTPzIzsG3PNsoxjEcPJdEseMnFUguzEK0GshMZy43dRKElc10wOiQRi3cGLub9Hl-YL-2KjjbzywoHwwZ9X3fAZdLGQDry+P8Lwqg4QLA7w-ECLwUDbUTfGYZD0kyTBauYfL5wqaQtPqLlmhaAjVCI7oOvHai2Vyq4+i2y9jwAHkO-JKtyzCLPKAT7FGkSENG8SMSkiyZJM+zDW4PBYUOlSovU8oFr5Jb4tHC9xUlMHIr7Mzt1ivi1Q1ZLDDhva4MerMsoQdc0uq+H6Ko4o7zh6b6rfXpus4XqIMCSEODg6EYAAcXzVlxtQqb0Lycydzm5muTwlp7HzTayovKrdrx-bDvGKZTvF9Bzs4oESd5hGbuhVnRlUEStfzZ7JJR97PINckwGcnM-ph0zCjLbk+QFMHEshgzoaTLyYvBZ3-2NxM7LNiltbUHloVhf7YbtjSHe0mAACpXalEWdZtz31e9ip+mTlAPWkOWAEYnwAZgAFieFDMkUorjy+HQEFAZsq8PGunmzr1m6mDjGj91LVfnUPYmD7Hcb7rdqKuKZs9UOXM76bPc4L4uy6mCvTWalZa-rkBG-XiYvjb5u+j6Ro9hgIm+fvbmwHKGAXwprO2Znh-RgX8pC9L8uJrXgKN6eOuG6bkxI+rd8ztyAV3A4pgeqeD6pBbAPgoDYG4PARyhhg4pC-lzHIM11bYVqA0Fa2cxYJX-O+A+oxz7HF7nOG+-RZYnTOvvUBh5WpHxVnOK6vEHLpkyLCOAqCh4oDRC9P2tkPoGjTCaYOsJyHuQ9q6bynoKxVlke2GM2dREyADimQ0qDLYyOYaMCOtt2RKL8jAWRdZFZJDCu7bRY9ro9EnvmV+MB34lzPj3Wi0sUE8JQII1cOMcp4wcXzCec8XF5zfkvTxO1SZX3Ju+ZxL8oluJiVTUCMDaYBEsCgDUEBkgwAAFIQCEizfMgR-7bywRhWa5RqiUiFkQ4I1j3xIOALkqAcAIACSgLMee0hKFqzOH3GW1i5Z9AViQi850WL106d03pEyADqLAPRLRaAAIR5AoOAABpJhKTF6l3YdxdOcUABWpS0DSJKUJQRwijbSX9uInR5tpEDOMV5KOnItJOwGTAAAYuEGoABZCx+ZU4KK9nFJxESjnRI-ivL+gD7idz-lvHeP8a6xLei802Oi-BaEyB8yJ1p5mUEWdAWYykoUlh+TASkVRpAKFBgC70ELRh0tCRrGAe5kk51Se4z+qFUUTM3gA3eUxwiWHYJMShU5qHAnKHcm5Bs1DZUunDcee4hn4zJrfBqSQeiQOgeBfqAQvAdPgN9WAwBsBIMIPERIGDObTU4SUepwNBbLVaMYSWSqCp9BALai6ISYV8RDemOEiBo2GyxJo+ROio14EZHoAw3LFEyEWlyeOidqyZoZd60GCd9JJ0hUmwG2aQa5tLVDCKlbtXXRgEjTUzzA3zljXgYewTR5NrCbqgNpwDV33fJA0wUDqZAA)

