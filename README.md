# Retropia [![Matrix]][matrix-community] [![Discord]][discord-guild] [![Maven Central]][maven-page] [![Docs]][documentation] [![Build]][gitlab] [![Coverage]][gitlab] [![Donate]][elypia-donate]
The [Gradle]/[Maven] import strings can be found at the maven-central badge above!

## About
This is a small library that adds implementions and utilities on top of [Retrofit]
and it's additional libraries.
This provides a [Singleton] for the `OkHttpClient` available at
`HttpClientSingleton`, generic `Interceptor` implementations for common
use cases such as adding query parameters or redis caching, deserializer 
implementions for `Gson`, and `Jaxb`, generic exceptions API wrappers may want to throw.

The scope of this project may grow as more functionality or abstraction
is desired.

## Support
Should any problems occur, come visit us over on Discord! We're always around and there are
ample developers that would be willing to help; if it's a problem with the library itself then we'll
make sure to get it sorted.

[matrix-community]: https://matrix.to/#/+elypia:matrix.org "Matrix Invite"
[discord-guild]: https://discord.gg/hprGMaM "Discord Invite"
[maven-page]: https://search.maven.org/search?q=g:org.elypia.retropia "Maven Central"
[documentation]: https://elypia.gitlab.io/retropia "Documentation"
[gitlab]: https://gitlab.com/Elypia/retropia/commits/master "Repository on GitLab"
[elypia-donate]: https://elypia.org/donate "Donate to Elypia"
[Gradle]: https://gradle.org/ "Depend via Gradle"
[Maven]: https://maven.apache.org/ "Depend via Maven"
[Retrofit]: https://square.github.io/retrofit/ "Retrofit on GitHub"
[Singleton]: https://en.wikipedia.org/wiki/Singleton_pattern "Singleton Pattern"
[Elypiai]: https://gitlab.com/Elypia/elypiai "Elypiai on GitLab"

[Matrix]: https://img.shields.io/matrix/elypia:matrix.org?logo=matrix "Matrix Shield"
[Discord]: https://discord.com/api/guilds/184657525990359041/widget.png "Discord Shield"
[Maven Central]: https://img.shields.io/maven-central/v/org.elypia.retropia/core "Download Shield"
[Docs]: https://img.shields.io/badge/docs-retropia-blue.svg "Documentation Shield"
[Build]: https://gitlab.com/Elypia/retropia/badges/master/pipeline.svg "GitLab Build Shield"
[Coverage]: https://gitlab.com/Elypia/retropia/badges/master/coverage.svg "GitLab Coverage Shield"
[Donate]: https://img.shields.io/badge/donate-elypia-blueviolet "Donate Shield"
